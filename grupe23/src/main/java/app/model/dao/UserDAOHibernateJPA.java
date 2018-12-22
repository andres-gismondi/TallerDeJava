package app.model.dao;

import app.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO{
    public UserDAOHibernateJPA(){
        super(User.class);
    }


    @Override
    @Transactional
    public User getUser(long id) {
        User userd = this.listar().stream().filter(b -> b.getId()==id).findFirst().orElse(null);
        if(userd!=null){
            Hibernate.initialize(userd.getCategories());
            Hibernate.initialize(userd.getBillboards());
        }

        return userd;
    }

    public List<User> listarUsuarios() {

        List<User> usuarios = super.listar();

        for (User u:usuarios) {
            Hibernate.initialize(u.getCategories());
            Hibernate.initialize(u.getBillboards());
        }
        return usuarios;
    }

    public User deleteUser(long id){
         return this.listar().stream().filter(b -> b.getId()==id).findFirst().orElse(null);
    }

    @Transactional
    public User getUserByEmail(String email){
        User userd = this.listar().stream().filter(b -> b.getEmail().equals(email)).findFirst().orElse(null);
        if(userd!=null){
            Hibernate.initialize(userd.getCategories());
            Hibernate.initialize(userd.getBillboards());
        }

        return userd;
    }

    @Transactional
    public long getIdFromUser(String email){
        User userd = this.listar().stream().filter(b -> b.getEmail().equals(email)).findFirst().orElse(null);
        if(userd!=null){
            Hibernate.initialize(userd.getCategories());
            Hibernate.initialize(userd.getBillboards());
        }

        return userd.getId();
    }

    public Boolean userHasCategory(String email, String name){
        User userCategory = this.getUserByEmail(email);
        if(userCategory!=null){
            if(userCategory.getCategories().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null)!=null){
                return true;
            }
        }
        return false;
    }

    public Boolean userHasBillboard(String email, String title){
        User userBillboard = this.getUserByEmail(email);
        if(userBillboard!=null){
            if(userBillboard.getBillboards().stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null)!=null){
                return true;
            }
        }
        return false;
    }



}