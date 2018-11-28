package spring.service;

import dao.DaoFactory;
import model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> listAllUsers() {
        List<User> users = DaoFactory.getUserDAO().listar();
        return users;
    }
}
