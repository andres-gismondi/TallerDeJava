package app.publication.repository;

import app.application.repository.GenericDAOHibernateJPA;
import app.publication.model.Commentary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommentaryDAOHibernateJPA extends GenericDAOHibernateJPA<Commentary> implements CommentaryDAO {
    public CommentaryDAOHibernateJPA(){
        super(Commentary.class);
    }

    @Override
    @Transactional
    public Commentary getCommentary(Commentary commentary){
        return this.listar().stream().filter(b -> b.getId()==commentary.getId()).findFirst().orElse(null);
    }

}


