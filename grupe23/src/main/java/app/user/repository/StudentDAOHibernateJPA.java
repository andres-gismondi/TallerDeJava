package app.user.repository;

import app.application.repository.GenericDAOHibernateJPA;
import app.user.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOHibernateJPA extends GenericDAOHibernateJPA<Student> implements StudentDAO {
    public StudentDAOHibernateJPA() {
        super(Student.class);
    }
}
