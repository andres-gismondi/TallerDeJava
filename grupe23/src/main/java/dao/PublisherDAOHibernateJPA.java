package dao;

import model.Publisher;

public class PublisherDAOHibernateJPA extends GenericDAOHibernateJPA<Publisher> implements PublisherDAO {
    public PublisherDAOHibernateJPA(){
        super(Publisher.class);
    }
}
