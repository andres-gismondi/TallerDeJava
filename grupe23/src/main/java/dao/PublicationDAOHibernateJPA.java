package dao;

import model.Publication;

import javax.persistence.Query;

public class PublicationDAOHibernateJPA extends GenericDAOHibernateJPA<Publication> implements PublicationDAO {
    public PublicationDAOHibernateJPA(){
        super(Publication.class);
    }
    public Publication getByName(String body){
        Query consulta = EMF.getEMF().createEntityManager().createQuery("select c  from " + getPersistentClass().getSimpleName()+ " c where c.body=:body");
        consulta.setParameter("body", body);

        return (Publication) consulta.setMaxResults(1).getResultList().get(0);
    }
}
