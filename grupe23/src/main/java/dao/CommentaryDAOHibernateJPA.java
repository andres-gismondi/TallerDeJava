package dao;

import model.Commentary;

public class CommentaryDAOHibernateJPA extends GenericDAOHibernateJPA<Commentary> implements CommentaryDAO {
    public CommentaryDAOHibernateJPA(){
        super(Commentary.class);
    }
}
