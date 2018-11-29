package service;

import dao.DaoFactory;
import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public UserService() {
    }

    public List<User> listAllUsers() {
        return userDAO.listar();
    }
}