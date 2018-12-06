package app.service;

import app.model.dao.*;
import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final static String TOKEN = "1234";

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

    public User setCategories(List<Category> categories, User user){

        User newUser = userDAO.getUser(userDAO.getIdFromUser(user.getEmail()));
        for (Category cat: categories) {
            if(!userDAO.userHasCategory(newUser.getEmail(),cat.getName())){
                Category category = categoryDAO.getCategory(cat.getName());
                newUser.addCategory(category);
            }
        }
        userDAO.actualizar(newUser);
        return newUser;
    }

    public HttpHeaders loginUser(String userName, String password, HttpHeaders response){
        User user = userDAO.getUserByEmail(userName);
        if(user!=null){
            if(user.getPassword().equals(password)){
                response.set("token",TOKEN);
            }
        }
        return response;
    }

    public User getUserById(long id,String token){
        if(token.equals(TOKEN)){
            if(userDAO.getUser(id)!=null){
                return userDAO.getUser(id);
            }
        }
        return null;

    }

    public Boolean deleteUserById(long id,String token){
        if(token.equals(TOKEN)){
            User user = userDAO.deleteUser(id);
            if(user!=null){
                userDAO.borrar(user);
                return true;
            }
            return false;
        }
        return false;
    }
}