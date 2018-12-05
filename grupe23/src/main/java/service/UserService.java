package service;

import dao.CategoryDAO;
import dao.CommunicationDAO;
import dao.DaoFactory;
import dao.UserDAO;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return userDAO.listarUsuarios();
    }

    public Boolean createUser(User user){

        User newUser = new User();
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setType(user.getType());

        userDAO.persistir(newUser);

        return true;
    }

    public Boolean setCategories(List<Category> categories, User user){
        //userDAO.getUser(user.getId()).getCategories().stream().forEach(c -> c.getName().equals(categories.stream().filter()));
        User u = userDAO.getUserByEmail(user.getEmail());
        List<Category> ccs = userDAO.getUserByEmail(user.getEmail()).getCategories().stream()
                .filter(c -> c.getName().equals(this.nameCategory(categories,c)))
                .map(c -> new Category(c.getId(),c.getName(),c.getWritePermisson()))
                .collect(Collectors.toList());

        return true;
    }

    private String nameCategory(List<Category> categories, Category c){
        Category cat = categories.stream().filter(cc -> cc.getName().equals(c.getName())).findFirst().orElse(null);
        if(cat!=null){
            return cat.getName();
        }
        return null;
    }

    private Category createCategory(Category category){
        Category cc = new Category();
        cc.setName(category.getName());
        cc.setWritePermisson(category.getWritePermisson());

        categoryDAO.persistir(cc);

        return cc;
    }

    public Boolean createCommunication(Communication communication){
        Communication communication1 = new Communication();
        communication1.setContact(communication.getContact());
        communication1.setType(communication.getType());

        communicationDAO.persistir(communication1);

        return true;

    }

    public User getUserById(long id){
        return userDAO.getUser(id);
    }

    public Boolean deleteUserById(long id){
        User user = userDAO.deleteUser(id);
        if(user!=null){
            userDAO.borrar(user);
            return true;
        }
        return false;
    }
}