package test;

import dao.CategoryDAO;
import dao.DaoFactory;
import dao.EMF;
import dao.UserDAO;
import model.Admin;
import model.Category;
import model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void createAndDeleteUserWithCategory(){
        //creando categoria
        Category cc = new Category();
        cc.setName("Hola");
        cc.setWritePermisson(true);

        //creando un usuario
        User p = new Admin();
        p.setFirstName("Andy");
        p.setLastName("Kato");
        p.setEmail("bruceLee@gmail.com");
        p.setType("User");
        p.addCategory(cc);

        //se le pide a daoFactory un usuarioDAO
        UserDAO user = DaoFactory.getUserDAO();
        user.persistir(p);

        List<User> users = EMF.getEMF().createEntityManager().createQuery("select u from User u").getResultList();
        User assertUser = new User();
        for (User u : users) {
            if(u.getFirstName().equals(p.getFirstName()) && u.getEmail().equals(p.getEmail())){
                assertUser = u;
            }
        }
        assertEquals("Andy",assertUser.getFirstName());
        assertEquals("Kato",assertUser.getLastName());
        assertEquals(assertUser.getCategories().size(),1);
        //borro el usuario
        user.borrar(assertUser.getId());
        //checkeo si se borro.
        assertEquals(false, (user.existe(assertUser.getId())));

        //borro la categoria creada
        CategoryDAO categoryDAO = DaoFactory.getCategoryDAO();
        categoryDAO.borrar(cc);

    }
}
