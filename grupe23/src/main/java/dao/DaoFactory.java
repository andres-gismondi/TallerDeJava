package dao;

public class DaoFactory {
    public static UserDAO getUserDAO() {

        return new UserDAOHibernateJPA();
    }
}
