package dao;

import model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOHibernateJPA extends GenericDAOHibernateJPA<Student> implements StudentDAO {
    public StudentDAOHibernateJPA() {
        super(Student.class);
    }
}
