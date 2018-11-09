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
<<<<<<< HEAD
    public static BillboardDAO getBillboardDao(){
=======
    public static BillboardDAO getBillboardDAO() {
>>>>>>> 4df1f815278b389751c097b8baa1ae9d57d3e859

        return new BillboardDAOHibernateJPA();
    }
}
