package app.user.repository;

import app.application.repository.GenericDAOHibernateJPA;
import app.user.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOHibernateJPA extends GenericDAOHibernateJPA<Admin> implements AdminDAO {
    public AdminDAOHibernateJPA(){
        super(Admin.class);
    }
}
