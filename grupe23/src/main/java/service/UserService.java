package service;

import dao.*;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
                response.set("token","1234");
            }
        }
        return response;
    }

    public User getUserById(long id,String token){
        if(token.equals("1234")){
            if(userDAO.getUser(id)!=null){
                return userDAO.getUser(id);
            }
        }
        return null;

    }

    public Boolean deleteUserById(long id,String token){
        if(token.equals("1234")){
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