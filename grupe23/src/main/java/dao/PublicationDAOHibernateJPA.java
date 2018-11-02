package dao;

import model.Publication;

public class PublicationDAOHibernateJPA extends GenericDAOHibernateJPA<Publication> implements PublicationDAO {
    public PublicationDAOHibernateJPA(){
        super(Publication.class);
    }
}
