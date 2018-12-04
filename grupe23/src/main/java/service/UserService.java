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
        return userDAO.listarUsuarios();
    }

    public Boolean createUser(User user){

        User newUser = new User();
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setType(user.getType());

        //creando categoria
        for (int i =0; i<user.getCategories().size();i++) {
            Category cr = categoryDAO.getCategory(user.getCategories().get(i).getName());
            if (categoryDAO.getCategory(user.getCategories().get(i).getName()) != null) {
                if(!userDAO.userHasCategory(user.getEmail(),user.getCategories().get(i).getName())){
                    newUser.addCategory(user.getCategories().get(i));
                }
            } else {
                //if(!userDAO.userHasCategory(user.getId(),user.getCategories().get(i).getName())){         }
                //Category category =this.createCategory(user.getCategories().get(i));
                newUser.addCategory(this.createCategory(user.getCategories().get(i)));
            }


        }

        if(userDAO.getUserByEmail(newUser.getEmail())!=null){
            userDAO.actualizar(newUser);
        }else{
            userDAO.persistir(newUser);

        }

        return true;
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