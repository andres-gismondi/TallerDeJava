package dao;

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
}
