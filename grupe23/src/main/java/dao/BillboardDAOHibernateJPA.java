package dao;

import model.Billboard;

import javax.persistence.Query;

public class BillboardDAOHibernateJPA extends GenericDAOHibernateJPA<Billboard> implements BillboardDAO {
    public BillboardDAOHibernateJPA(){
        super(Billboard.class);
    }

    public Billboard getByName(String description){
        Query consulta = EMF.getEMF().createEntityManager().createQuery("select c  from " + getPersistentClass().getSimpleName()+ " c where c.description=:description");
        consulta.setParameter("description", description);

        return (Billboard) consulta.setMaxResults(1).getResultList().get(0);
    }
}
