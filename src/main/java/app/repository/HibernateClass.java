package app.repository;

import app.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateClass {

    public SessionFactory sessionFactory;


    @Autowired
    public HibernateClass(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Student> getAllStudents() {
        Session currentSession = sessionFactory.openSession();
        currentSession.getTransaction().begin();

        List <Student> fromStudent = currentSession.createQuery("from Student", Student.class).getResultList();
        currentSession.getTransaction().commit();

        currentSession.close();

        return fromStudent;

        /*
        НЕПРАВИЛЬНЫЙ СПОСОБ

        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.beginTransaction();

        List <Student> fromStudent = currentSession.createQuery("from Student", Student.class).getResultList();
        currentSession.getTransaction().commit();
*/

    }

    public void saveStudent(Student student) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        session.saveOrUpdate(student);
        session.getTransaction().commit();
        session.close();

    }

    public Optional <Student> findById(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query<Student> student = session.createQuery("from Student where id =:id", Student.class);
        student.setParameter("id", id);

        session.getTransaction().commit();

        return student.stream().findFirst();

    }


    public void deleteById(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query<Student> student = session.createQuery("delete from Student where id =:id");
        student.setParameter("id", id);
        student.executeUpdate();

        session.getTransaction().commit();
    }


    public Student updateById(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Student student = session.get(Student.class, id);

        //session.saveOrUpdate(student);
        session.getTransaction().commit();
        session.close();
        return student;
    }
}
