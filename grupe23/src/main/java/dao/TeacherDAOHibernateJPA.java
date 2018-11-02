package dao;

import model.Teacher;
import dao.TeacherDAO;

public class TeacherDAOHibernateJPA extends GenericDAOHibernateJPA<Teacher> implements TeacherDAO {
    public TeacherDAOHibernateJPA() {
        super(Teacher.class);
    }
}
