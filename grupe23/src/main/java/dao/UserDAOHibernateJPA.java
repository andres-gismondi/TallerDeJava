package dao;

import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO{
    public UserDAOHibernateJPA(){
        super(User.class);
    }


    @Override
    public User getUser(User user) {
        return this.listar().stream().filter(b -> b.getId()==user.getId()).findFirst().orElse(null);
    }



}