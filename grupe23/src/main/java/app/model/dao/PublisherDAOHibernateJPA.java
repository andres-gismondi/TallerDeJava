package app.model.dao;

import app.model.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherDAOHibernateJPA extends GenericDAOHibernateJPA<Publisher> implements PublisherDAO {
    public PublisherDAOHibernateJPA(){
        super(Publisher.class);
    }
}
