package dao;

import model.Billboard;

import javax.persistence.Query;

public class BillboardDAOHibernateJPA extends GenericDAOHibernateJPA<Billboard> implements BillboardDAO {
    public BillboardDAOHibernateJPA(){
        super(Billboard.class);
    }

    public Billboard getBillboard(Billboard billboard){
        return this.listar().stream().filter(b -> b.getId()==billboard.getId()).findFirst().orElse(null);

    }
}
