import dao.DaoFactory;
import dao.UserDAO;
import dao.UserDAOHibernateJPA;
import model.*;

import java.util.List;

public class App {
    public static void main(String[] args) {

        //se crea categoria
        Category c = new Category();
        c.setName("Teacher");
        c.setWritePermisson(true);

        //se crea a un usuario
        User p = new Admin();
        p.setFirstName("Andy");
        p.setLastName("Kato");
        p.setEmail("bruceLee@gmail.com");
        p.setType("User");
        p.addCategory(c);

        //se le pide a daoFactory un usuarioDAO
        UserDAO user = DaoFactory.getUserDAO();

        //se agrega un usuario a la bd
        user.persistir(p);

        //se listan los usuarios
        List<User> users = user.listar();
        //User users = user.getUser("bruce");
        for (User user1 : users) {
            System.out.println(user1.getFirstName());

        }

        //se borra un usuario. este no anda, hay que consultar.
       user.borrar(10);

    }
}


