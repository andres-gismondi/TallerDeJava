package app.application.repository;

import app.billboard.repository.BillboardDAO;
import app.billboard.repository.BillboardDAOHibernateJPA;
import app.category.repository.CategoryDAO;
import app.category.repository.CategoryDAOHibernateJPA;
import app.publication.repository.CommentaryDAO;
import app.publication.repository.CommentaryDAOHibernateJPA;
import app.publication.repository.PublicationDAO;
import app.publication.repository.PublicationDAOHibernateJPA;
import app.user.repository.UserDAO;
import app.user.repository.UserDAOHibernateJPA;

public class DaoFactory {
    public static UserDAO getUserDAO() {

        return new UserDAOHibernateJPA();
    }

    public static PublicationDAO getPublicationDAO() {

        return new PublicationDAOHibernateJPA();
    }
    public static CommentaryDAO getCommentaryDao() {

        return new CommentaryDAOHibernateJPA();
    }
    public static CategoryDAO getCategoryDAO() {

        return new CategoryDAOHibernateJPA();
    }

    public static BillboardDAO getBillboardDAO() {

        return new BillboardDAOHibernateJPA();
    }
}
