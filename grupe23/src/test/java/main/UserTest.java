package main;

import app.config.AppConfig;
import app.config.PersistenceConfig;
import app.config.SpringWebApp;
import app.model.dao.UserDAO;
import app.model.Admin;
import app.model.Category;
import app.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
        cc.setName("cadp");
        cc.setWritePermisson(true);

        //creando un usuario
        User p = new Admin();
        p.setFirstName("walter");
        p.setLastName("walter");
        p.setEmail("walter@walter.com");
        p.setType("admin");
        p.setPassword("walter1234");
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


        //Assert.assertEquals("Andy",assertUser.getFirstName());
        //Assert.assertEquals("Kato",assertUser.getLastName());
       // Assert.assertEquals(assertUser.getCategories().size(),1);
        //borro el usuario
        //userDAO.borrar(assertUser);
        //checkeo si se borro.
        Boolean existe = userDAO.existe((long)assertUser.getId());
        //Assert.assertEquals(false, (userDAO.existe((long)assertUser.getId())));

        //borro la categoria creada
        //CategoryDAO categoryDAO = DaoFactory.getCategoryDAO();
        //categoryDAO.borrar(cc);

    }
}
