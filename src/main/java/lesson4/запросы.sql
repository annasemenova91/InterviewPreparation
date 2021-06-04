CREATE SCHEMA cinema ;

CREATE TABLE films (
  id INT NOT NULL,
  title VARCHAR(300) NOT NULL,
  duration INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE film_sessions (
  id INT NOT NULL,
  film_id INT NOT NULL,
  start_time DATETIME NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (id),
  INDEX film_id_idx (film_id ASC) VISIBLE,
  CONSTRAINT film_id
    FOREIGN KEY (film_id)
    REFERENCES films (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE tickets (
  id INT NOT NULL,
  film_session_id INT NOT NULL,
  PRIMARY KEY (id));

INSERT INTO films (id, title, duration) VALUES ('1', 'Film 1', '60');
INSERT INTO films (id, title, duration) VALUES ('2', 'Film 2', '120');
INSERT INTO films (id, title, duration) VALUES ('3', 'Film 3', '90');
INSERT INTO films (id, title, duration) VALUES ('4', 'Film 4', '60');
INSERT INTO films (id, title, duration) VALUES ('5', 'Film 5', '120');

INSERT INTO film_sessions (id, film_id, start_time, price) VALUES ('1', '3', '2021-06-02 10:30:00', '500');
INSERT INTO film_sessions (id, film_id, start_time, price) VALUES ('2', '1', '2021-06-02 12:30:00', '400');
INSERT INTO film_sessions (id, film_id, start_time, price) VALUES ('3', '2', '2021-06-02 14:00:00', '450');
INSERT INTO film_sessions (id, film_id, start_time, price) VALUES ('4', '5', '2021-06-02 17:30:00', '550');

INSERT INTO tickets (id, film_session_id) VALUES ('1', '1');
INSERT INTO tickets (id, film_session_id) VALUES ('2', '1');
INSERT INTO tickets (id, film_session_id) VALUES ('3', '1');
INSERT INTO tickets (id, film_session_id) VALUES ('4', '2');
INSERT INTO tickets (id, film_session_id) VALUES ('5', '2');
INSERT INTO tickets (id, film_session_id) VALUES ('6', '3');
INSERT INTO tickets (id, film_session_id) VALUES ('7', '4');

-- ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. 
-- Выводить надо колонки «фильм 1», «время начала», «длительность», «фильм 2», «время начала», «длительность»;
select
films.title,
film_sessions.start_time,
films.duration,
film_sessions_second.title,
film_sessions_second.duration,
film_sessions_second.start_time
from film_sessions 
	join films on film_sessions.film_id = films.id
	join (select
		films.title,
        film_sessions.film_id,
		film_sessions.start_time,
		films.duration,
		film_sessions.start_time + INTERVAL films.duration MINUTE as end_time
		from film_sessions 
			join films on film_sessions.film_id = films.id) as film_sessions_second
	on (film_sessions.start_time + INTERVAL films.duration MINUTE > film_sessions_second.start_time) 
    and (film_sessions.start_time + INTERVAL films.duration MINUTE < film_sessions_second.end_time)
    and film_sessions.film_id != film_sessions_second.film_id
order by film_sessions.start_time;

-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. 
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»;
select
films.title,
film_sessions.start_time,
films.duration,
film_sessions_second.start_time,
timediff(min(film_sessions_second.start_time), film_sessions.start_time + INTERVAL films.duration MINUTE) as break
from film_sessions 
	join films on film_sessions.film_id = films.id
	left join film_sessions film_sessions_second on film_sessions.start_time < film_sessions_second.start_time 
 group by film_sessions.start_time
 having time_to_sec(break) > 60 * 30
 order by break desc;

-- список фильмов, для каждого — с указанием общего числа посетителей за все время, среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли). 
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу;

(select 
films.title,
count(distinct tickets.id) as total,
sum(film_sessions.price) as total_money,
count(distinct tickets.id) / count(distinct tickets.film_session_id) as avg
from tickets
	join film_sessions on tickets.film_session_id = film_sessions.id
    join films on film_sessions.film_id = films.id
group by films.title
order by total_money desc
)
union
select 
"Итого", 
count(*), 
sum(film_sessions.price),
count(*) / count(distinct tickets.film_session_id)
from tickets 
	join film_sessions on tickets.film_session_id = film_sessions.id;

-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма: с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.)
select 
case
    when hour(film_sessions.start_time) >= 9 and hour(film_sessions.start_time) <= 15
        then '9:00 - 15:00'
    when hour(film_sessions.start_time) > 15 and hour(film_sessions.start_time) <= 18 
        then '15:00 - 18:00'
    when hour(film_sessions.start_time) > 18 and hour(film_sessions.start_time) <= 21 
        then '18:00 - 21:00'
	when hour(film_sessions.start_time) > 21  
        then '21:00 - 00:00'
end as hour,
sum(price), 
count(*) 
from film_sessions
	join tickets on film_sessions.id = tickets.film_session_id
group by hour;

-- Все сеансы
select
films.title,
film_sessions.start_time,
films.duration,
film_sessions.start_time + INTERVAL films.duration MINUTE as end_time
from film_sessions 
join films on film_sessions.film_id = films.id 
