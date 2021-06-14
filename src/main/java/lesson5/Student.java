package lesson5;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mark", nullable = false)
    private Integer mark;

    public Student() {
    }

    public Student(Long id, String name, Integer mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public Student(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mark=" + mark +
                '}';
    }
}
