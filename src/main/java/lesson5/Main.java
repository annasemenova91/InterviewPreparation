package lesson5;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentDAOImpl dao = new StudentDAOImpl();
        String name = "Name ";
        for (int i = 0; i < 1000; i++) {
            dao.save(new Student(name + i, 1 + (int) (Math.random() * 5)));
        }
        Student student = new Student("Name", 5);
        dao.save(student);
        student.setMark(4);
        dao.update(student);
        Student anotherStudent = dao.findById(1L);
        System.out.println(anotherStudent);
        dao.delete(anotherStudent);
        List<Student> list = dao.findAll();
        for (Student st : list) {
            System.out.println(st);
        }
    }
}
