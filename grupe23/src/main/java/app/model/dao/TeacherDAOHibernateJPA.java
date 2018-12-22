package app.model.dao;

import app.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOHibernateJPA extends GenericDAOHibernateJPA<Teacher> implements TeacherDAO {
    public TeacherDAOHibernateJPA() {
        super(Teacher.class);
    }
}
