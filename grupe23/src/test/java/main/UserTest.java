package main;

import config.AppConfig;
import config.PersistenceConfig;
import config.SpringWebApp;
import dao.CategoryDAO;
import dao.DaoFactory;
import dao.EMF;
import dao.UserDAO;
import model.Admin;
import model.Category;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, PersistenceConfig.class, SpringWebApp.class} )
@WebAppConfiguration
public class UserTest {

    @Autowired
    UserDAO userDAO;

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
        //UserDAO userDAO = DaoFactory.getUserDAO();
        userDAO.persistir(p);

        List<User> users = userDAO.listar();
        User assertUser = new User();
        for (User u : users) {
            if(u.getFirstName().equals(p.getFirstName()) && u.getEmail().equals(p.getEmail())){
                assertUser = u;
            }
        }


        Assert.assertEquals("Andy",assertUser.getFirstName());
        Assert.assertEquals("Kato",assertUser.getLastName());
       // Assert.assertEquals(assertUser.getCategories().size(),1);
        //borro el usuario
        userDAO.borrar(assertUser);
        //checkeo si se borro.
        Boolean existe = userDAO.existe((long)assertUser.getId());
        Assert.assertEquals(false, (userDAO.existe((long)assertUser.getId())));

        //borro la categoria creada
        //CategoryDAO categoryDAO = DaoFactory.getCategoryDAO();
        //categoryDAO.borrar(cc);

    }
}
