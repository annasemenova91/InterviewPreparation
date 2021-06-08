package lesson5;

import java.util.List;

public interface StudentDAO {
    public Student findById(Long id);

    public void save(Student user);

    public void update(Student user);

    public void delete(Student user);

    public List<Student> findAll();
}
