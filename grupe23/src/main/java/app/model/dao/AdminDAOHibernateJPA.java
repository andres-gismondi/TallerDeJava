package app.model.dao;

import app.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOHibernateJPA extends GenericDAOHibernateJPA<Admin> implements AdminDAO {
    public AdminDAOHibernateJPA(){
        super(Admin.class);
    }
}
