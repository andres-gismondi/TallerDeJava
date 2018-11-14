package dao;

import model.Publication;

import javax.persistence.Query;

public class PublicationDAOHibernateJPA extends GenericDAOHibernateJPA<Publication> implements PublicationDAO {
    public PublicationDAOHibernateJPA(){
        super(Publication.class);
    }


    @Override
    public Publication getPublication(Publication publication) {
        return this.listar().stream().filter(b -> b.getId()==publication.getId()).findFirst().orElse(null);
    }
}
