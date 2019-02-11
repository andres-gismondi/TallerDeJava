package app.user.repository;

import app.application.repository.GenericDAOHibernateJPA;
import app.user.model.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherDAOHibernateJPA extends GenericDAOHibernateJPA<Publisher> implements PublisherDAO {
    public PublisherDAOHibernateJPA(){
        super(Publisher.class);
    }
}
