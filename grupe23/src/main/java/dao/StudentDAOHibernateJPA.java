package dao;

import model.Student;

public class StudentDAOHibernateJPA extends GenericDAOHibernateJPA<Student> implements StudentDAO {
    public StudentDAOHibernateJPA() {
        super(Student.class);
    }
}
