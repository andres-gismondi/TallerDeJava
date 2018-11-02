package dao;

import model.Admin;

public class AdminDAOHibernateJPA extends GenericDAOHibernateJPA<Admin> implements AdminDAO {
    public AdminDAOHibernateJPA(){
        super(Admin.class);
    }
}
