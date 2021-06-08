package lesson5;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Session currentSession;

    public StudentDAOImpl() {
        openCurrentSession();
    }

    public void openCurrentSession() {
        currentSession = HibernateSessionFactory.getSessionFactory().openSession();
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    @Override
    public Student findById(Long id) {
        return (Student) currentSession.get(Student.class, id);
    }

    @Override
    public void save(Student user) {
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(user);
        transaction.commit();
    }

    @Override
    public void update(Student user) {
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(user);
        transaction.commit();
    }

    @Override
    public void delete(Student user) {
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(user);
        transaction.commit();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = (List<Student>) currentSession.createQuery("From Student").list();
        return students;
    }
}
