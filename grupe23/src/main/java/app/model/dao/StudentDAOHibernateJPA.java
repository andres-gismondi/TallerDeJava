package app.model.dao;

import app.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOHibernateJPA extends GenericDAOHibernateJPA<Student> implements StudentDAO {
    public StudentDAOHibernateJPA() {
        super(Student.class);
    }
}
