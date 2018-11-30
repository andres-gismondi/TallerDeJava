package dao;


import model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDAO extends GenericDAO<User>{

    public User getUser(User user);

    public List<User> listarUsuarios();
}
