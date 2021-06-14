CREATE SCHEMA `students` ;
CREATE TABLE `students`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `mark` INT NOT NULL,
  PRIMARY KEY (`id`));
