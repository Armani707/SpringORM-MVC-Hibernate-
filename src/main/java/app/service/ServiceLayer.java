package app.service;

import app.model.Student;
import app.repository.HibernateClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {


    HibernateClass hibernateClass;


    @Autowired
    public ServiceLayer(HibernateClass hibernateClass) {
        this.hibernateClass = hibernateClass;
    }

    @Transactional
    public List<Student> findAllStudents() {
        List<Student> allStudents = hibernateClass.getAllStudents();
        return allStudents;
    }


    public void saveOrUpdate(Student student) {
        hibernateClass.saveStudent(student);
    }

    public Optional <Student> findById(int id) {
        Optional<Student> byId = hibernateClass.findById(id);
        return byId;
    }

    public void deleteById(int id) {
        hibernateClass.deleteById(id);
    }

    public Student updateById(int id) {
        Student student = hibernateClass.updateById(id);
        return student;
    }
}
