package dao;


import model.Category;
import model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDAO extends GenericDAO<User>{

    public User getUser(long id);

    public List<User> listarUsuarios();

    public User deleteUser(long id);

    public Boolean userHasCategory(String email, String name);

    public User getUserByEmail(String email);

    public long getIdFromUser(String email);
}
