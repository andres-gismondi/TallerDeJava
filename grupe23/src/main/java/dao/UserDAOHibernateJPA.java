package dao;

import model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO{
    public UserDAOHibernateJPA(){
        super(User.class);
    }


    @Override
    @Transactional
    public User getUser(User user) {
        User userd = this.listar().stream().filter(b -> b.getId()==user.getId()).findFirst().orElse(null);
        Hibernate.initialize(userd.getCategories());
        return userd;
    }

    public List<User> listarUsuarios() {
       
        List<User> usuarios = super.listar();

        for (User u:usuarios) {
            Hibernate.initialize(u.getCategories());
        }
        return usuarios;
    }



}