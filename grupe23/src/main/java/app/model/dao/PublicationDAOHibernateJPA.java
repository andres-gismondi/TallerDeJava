package app.model.dao;

import app.model.Publication;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PublicationDAOHibernateJPA extends GenericDAOHibernateJPA<Publication> implements PublicationDAO {
    public PublicationDAOHibernateJPA(){
        super(Publication.class);
    }


    @Override
    @Transactional
    public Publication getPublication(String title) {
        Publication publication= this.listar().stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null);
        if(publication!=null){
            Hibernate.initialize(publication.getCommentaries());
        }
        return publication;
    }

    @Override
    @Transactional
    public List<Publication> getPublications(String title){
        List<Publication> publications = this.listar();
        List<Publication> returnPublications = new ArrayList<>();
        for (Publication pub:publications) {
            if(pub.getBillboard().getTitle().equals(title)){
                Hibernate.initialize(pub.getCommentaries());
                returnPublications.add(pub);
            }
        }
        return returnPublications;
    }
}
