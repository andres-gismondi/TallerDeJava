package app.user.service;

import app.billboard.controller.http.HttpResponseBillboard;
import app.billboard.model.Billboard;
import app.billboard.repository.BillboardDAO;
import app.category.model.Category;
import app.category.repository.CategoryDAO;
import app.publication.repository.CommunicationDAO;
import app.publication.repository.PublicationDAO;
import app.application.utils.*;
import app.user.controller.http.response.HttpResponseUserLogin;
import app.user.mapper.ResponseUserHeader;
import app.user.model.User;
import app.user.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    public ResponseUserHeader loginUser(String userName, String password, HttpHeaders response) {
        ResponseUserHeader responseUserHeader = new ResponseUserHeader();
        HttpResponseUserLogin httpResponseUserLogin = new HttpResponseUserLogin();
        User user = userDAO.getUserByEmail(userName);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                httpResponseUserLogin.setEmail(user.getEmail());
                httpResponseUserLogin.setFirstName(user.getFirstName());
                httpResponseUserLogin.setLastName(user.getLastName());
                httpResponseUserLogin.setType(user.getType());
                httpResponseUserLogin.setId(user.getId());
                response.set("Authorization", userDAO.getUserByEmail(user.getEmail()).getId() + "-" + UtilsImplementation.TOKEN);
                response.set("Access-Control-Expose-Headers", "Authorization");
            }
        }
        responseUserHeader.setHeaders(response);
        responseUserHeader.setHttpResponseUserLogin(httpResponseUserLogin);
        return responseUserHeader;
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

    private List<HttpResponseBillboard> setNewBillboards(List<Billboard> billboards) {
        List<HttpResponseBillboard> httpResponseBillboards = new ArrayList<>();
        for (Billboard bill : billboards) {
            HttpResponseBillboard httpResponseBillboard = new HttpResponseBillboard(bill.getId(), bill.getCategories(), bill.getTitle(), bill.getDescription(), bill.getDate());
            httpResponseBillboards.add(httpResponseBillboard);
        }

        return httpResponseBillboards;
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