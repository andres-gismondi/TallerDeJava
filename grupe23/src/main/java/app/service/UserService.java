package app.service;

import app.model.dao.*;
import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final static String TOKEN = "123456";
    private final static String ADMIN = "admin";
    private final static String PERMISSON_DENIED = "No posee los permisos suficientes";
    private final static String ACCESS_DENIED = "Token de acceso incorrecto";
    private final static String SUCCESS = "acceso correcto";

    @Autowired
    UserDAO userDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    CommunicationDAO communicationDAO;

    @Autowired
    BillboardDAO billboardDAO;

    @Autowired
    PublicationDAO publicationDAO;

    public UserService() {
    }

    public List<User> listAllUsers() {
        return userDAO.listarUsuarios();
    }

    public String createUser(User user,String token){

        if(token.equals(userDAO.getUserByEmail(user.getEmail()).getId()+"-"+TOKEN)){
            if(userDAO.getUserByEmail(user.getEmail()).getType().equals(ADMIN)){
                User newUser = new User();
                newUser.setPassword(user.getPassword());
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setType(user.getType());

                userDAO.persistir(newUser);
                return SUCCESS;
            }
            return PERMISSON_DENIED;
        }
        return ACCESS_DENIED;
    }

    public String setCategories(List<Category> categories, User user, String token){

        if(token.equals(userDAO.getUserByEmail(user.getEmail()).getId()+"-"+TOKEN)){
            if(userDAO.getUserByEmail(user.getEmail()).getType().equals(ADMIN)){
                User newUser = userDAO.getUser(userDAO.getIdFromUser(user.getEmail()));
                for (Category cat: categories) {
                    if(!userDAO.userHasCategory(newUser.getEmail(),cat.getName())){
                        Category category = categoryDAO.getCategory(cat.getName());
                        newUser.addCategory(category);
                    }
                }
                userDAO.actualizar(newUser);
                return SUCCESS;
            }
            return PERMISSON_DENIED;
        }
        return ACCESS_DENIED;



    }

    public HttpHeaders loginUser(String userName, String password, HttpHeaders response){
        User user = userDAO.getUserByEmail(userName);
        if(user!=null){
            if(user.getPassword().equals(password)){
                //response.set("Access-Control-Expose-Headers", "Authorization");
                //response.set("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
                response.set("token",userDAO.getUserByEmail(user.getEmail()).getId()+"-"+TOKEN);
            }
        }
        return response;
    }

    public User getUserById(long id,String token){
        if(token.equals(userDAO.getUser(id).getId()+"-"+TOKEN)){
            if(userDAO.getUser(id)!=null){
                return userDAO.getUser(id);
            }
        }
        return null;

    }

    public Boolean deleteUserById(long id,String token){
        if(token.equals(userDAO.getUser(id).getId()+"-"+TOKEN)){
            User user = userDAO.deleteUser(id);
            if(user!=null){
                userDAO.borrar(user);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<BillboardsUser>  getBillboards(String userName,String token){
        if(token.equals(userDAO.getUserByEmail(userName).getId()+"-"+TOKEN)){
            List<Billboard> billboards = billboardDAO.getBillboards();
            List<Billboard> returnBillboards = new ArrayList<>();
            for (Billboard bill:billboards) {
                if(bill.getCreator().getEmail().equals(userName)){
                    returnBillboards.add(bill);
                }
            }
            return this.setNewBillboards(returnBillboards);
        }
        return null;
    }

    private List<BillboardsUser> setNewBillboards(List<Billboard> billboards){
        List<BillboardsUser> billboardsUsers= new ArrayList<>();
        for (Billboard bill:billboards) {
            BillboardsUser billboardsUser = new BillboardsUser(bill.getId(),bill.getCategories(),bill.getTitle(),bill.getDescription(),bill.getDate());
            billboardsUsers.add(billboardsUser);
        }

        return billboardsUsers;
    }

    public String createBillboard(Billboard billboard,User user,String token){
        if(token.equals(userDAO.getUserByEmail(user.getEmail()).getId()+"-"+TOKEN)){
            if(userDAO.getUserByEmail(user.getEmail()).getType().equals(ADMIN)){
                Billboard bill = new Billboard();
                bill.setTitle(billboard.getTitle());
                bill.setDescription(billboard.getDescription());
                bill.setDate(billboard.getDate());

                User u = userDAO.getUserByEmail(user.getEmail());

                bill.setCreator(u);

                billboardDAO.persistir(bill);
                return SUCCESS;
            }
            return PERMISSON_DENIED;
        }
        return ACCESS_DENIED;
    }
}