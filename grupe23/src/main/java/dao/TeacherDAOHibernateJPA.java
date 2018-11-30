package dao;

import model.Teacher;
import dao.TeacherDAO;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOHibernateJPA extends GenericDAOHibernateJPA<Teacher> implements TeacherDAO {
    public TeacherDAOHibernateJPA() {
        super(Teacher.class);
    }
}
