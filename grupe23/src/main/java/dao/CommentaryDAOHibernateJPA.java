package dao;

import model.Commentary;

public class CommentaryDAOHibernateJPA extends GenericDAOHibernateJPA<Commentary> implements CommentaryDAO {
    public CommentaryDAOHibernateJPA(){
        super(Commentary.class);
    }

    public Commentary getCommentary(Commentary commentary){
        return this.listar().stream().filter(b -> b.getId()==commentary.getId()).findFirst().orElse(null);
    }

}


