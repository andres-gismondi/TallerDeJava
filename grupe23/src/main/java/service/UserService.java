package service;

import dao.CategoryDAO;
import dao.CommunicationDAO;
import dao.DaoFactory;
import dao.UserDAO;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    CommunicationDAO communicationDAO;

    public UserService() {
    }

    public List<User> listAllUsers() {
        return userDAO.listar();
    }

    public Boolean createUser(User user){
        //creando categoria
        Category cc = new Category();
        cc.setName(user.getCategories().get(0).getName());
        cc.setWritePermisson(user.getCategories().get(0).getWritePermisson());

        //creando un usuario
        User p = new Admin();
        p.setFirstName("Andy");
        p.setLastName("Kato");
        p.setEmail("bruceLee@gmail.com");
        p.setType("User");
        p.addCategory(cc);

        userDAO.persistir(p);

        return true;
    }

    public Boolean createCategory(Category category){
        Category cc = new Category();
        cc.setName(category.getName());
        cc.setWritePermisson(category.getWritePermisson());

        categoryDAO.persistir(cc);

        return true;
    }

    public Boolean createCommunication(Communication communication){
        Communication communication1 = new Communication();
        communication1.setContact(communication.getContact());
        communication1.setType(communication.getType());

        communicationDAO.persistir(communication1);

        return true;

    }
}