package app.user.repository;


import app.application.repository.GenericDAO;
import app.user.model.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    public User getUser(long id);

    public List<User> listarUsuarios();

    public User deleteUser(long id);

    public Boolean userHasCategory(String email, String name);

    public User getUserByEmail(String email);

    public long getIdFromUser(String email);

    public Boolean userHasBillboard(String email, String title);
}
