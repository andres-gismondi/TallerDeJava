package app.user.repository;

import app.application.repository.GenericDAOHibernateJPA;
import app.user.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOHibernateJPA extends GenericDAOHibernateJPA<Teacher> implements TeacherDAO {
    public TeacherDAOHibernateJPA() {
        super(Teacher.class);
    }
}
