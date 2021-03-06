package app.service;

import app.model.dao.*;
import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserService {

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

    public List<User> listAllUsers(String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getType().equals(UtilsImplementation.ADMIN)) {
                return userDAO.listarUsuarios();
            }
            return null;
        }
        return null;
    }

    public String createUser(User user, String token) {

        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getType().equals(UtilsImplementation.ADMIN)) {
                User newUser = new User();
                newUser.setPassword(user.getPassword());
                newUser.setEmail(user.getEmail());
                newUser.setFirstName(user.getFirstName());
                newUser.setLastName(user.getLastName());
                newUser.setType(user.getType());

                userDAO.persistir(newUser);
                return UtilsImplementation.SUCCESS;
            }
            return UtilsImplementation.PERMISSON_DENIED;
        }
        return UtilsImplementation.ACCESS_DENIED;
    }

    public String setCategories(List<Category> categories, User user, String token) {

        if (token.equals(userDAO.getUserByEmail(user.getEmail()).getId() + "-" + UtilsImplementation.TOKEN)) {
            User newUser = userDAO.getUser(userDAO.getIdFromUser(user.getEmail()));
            for (Category cat : categories) {
                if (!userDAO.userHasCategory(newUser.getEmail(), cat.getName())) {
                    Category category = categoryDAO.getCategory(cat.getName());
                    newUser.addCategory(category);
                }
            }
            userDAO.actualizar(newUser);
            return UtilsImplementation.SUCCESS;
        }
        return UtilsImplementation.ACCESS_DENIED;

    }

    public String userLikesBillboard(String title, String userName, String token) {
        long id = userDAO.getIdFromUser(userName);
        if (token.equals(id + "-" + UtilsImplementation.TOKEN)) {
            User newUser = userDAO.getUser(userDAO.getIdFromUser(userName));
            if (!userDAO.userHasBillboard(newUser.getEmail(), title)) {
                Billboard billboardToAdd = billboardDAO.getBillboardByTitle(title);
                newUser.addBillboard(billboardToAdd);
            }
            userDAO.actualizar(newUser);
            return UtilsImplementation.SUCCESS;
        }
        return UtilsImplementation.ACCESS_DENIED;
    }

    public UserLogedHeader loginUser(String userName, String password, HttpHeaders response) {
        UserLogedHeader userLogedHeader = new UserLogedHeader();
        UserLogin userLogin = new UserLogin();
        User user = userDAO.getUserByEmail(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                userLogin.setEmail(user.getEmail());
                userLogin.setFirstName(user.getFirstName());
                userLogin.setLastName(user.getLastName());
                userLogin.setType(user.getType());
                userLogin.setId(user.getId());
                response.set("Authorization", userDAO.getUserByEmail(user.getEmail()).getId() + "-" + UtilsImplementation.TOKEN);
                response.set("Access-Control-Expose-Headers", "Authorization");
            }
        }
        userLogedHeader.setHeaders(response);
        userLogedHeader.setUserLogin(userLogin);
        return userLogedHeader;
    }

    public User getUserById(long id, String token) {
        if (token.equals(userDAO.getUser(id).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUser(id) != null) {
                return userDAO.getUser(id);
            }
        }
        return null;

    }

    public Boolean deleteUserById(long id, String token) {
        if (token.equals(userDAO.getUser(id).getId() + "-" + UtilsImplementation.TOKEN)) {
            User user = userDAO.deleteUser(id);
            if (user != null) {
                userDAO.borrar(user);
                return true;
            }
            return false;
        }
        return false;
    }

    public List<Billboard> getBillboards(String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            User user = userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token));
            List<Billboard> returnBillboards = new ArrayList<>();
            for (Billboard bill:user.getBillboards()) {
                returnBillboards.add(billboardDAO.getBillboard(bill.getId()));
            }
            return returnBillboards;
        }
        return null;
    }

    private List<BillboardsUser> setNewBillboards(List<Billboard> billboards) {
        List<BillboardsUser> billboardsUsers = new ArrayList<>();
        for (Billboard bill : billboards) {
            BillboardsUser billboardsUser = new BillboardsUser(bill.getId(), bill.getCategories(), bill.getTitle(), bill.getDescription(), bill.getDate());
            billboardsUsers.add(billboardsUser);
        }

        return billboardsUsers;
    }

    public String createBillboard(Billboard billboard, User user, String token) {
        if (token.equals(userDAO.getUserByEmail(user.getEmail()).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUserByEmail(user.getEmail()).getType().equals(UtilsImplementation.ADMIN)) {
                Billboard bill = new Billboard();
                bill.setTitle(billboard.getTitle());
                bill.setDescription(billboard.getDescription());
                bill.setDate(billboard.getDate());

                User u = userDAO.getUserByEmail(user.getEmail());

                bill.setCreator(u);

                billboardDAO.persistir(bill);
                return UtilsImplementation.SUCCESS;
            }
            return UtilsImplementation.PERMISSON_DENIED;
        }
        return UtilsImplementation.ACCESS_DENIED;
    }
}