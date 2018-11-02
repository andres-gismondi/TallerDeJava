package dao;

import model.Billboard;

public class BillboardDAOHibernateJPA extends GenericDAOHibernateJPA<Billboard> implements BillboardDAO {
    public BillboardDAOHibernateJPA(){
        super(Billboard.class);
    }
}
