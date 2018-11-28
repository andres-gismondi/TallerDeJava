package spring.service;

import dao.DaoFactory;
import model.User;

import java.util.List;

public class UserService {

    public List<User> listAllUsers() {
        List<User> users = DaoFactory.getUserDAO().listar();
        return users;
    }
}
