package dao;

import model.User;
import javax.persistence.Query;

public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO{
    public UserDAOHibernateJPA(){
        super(User.class);
    }


    /** esté método es a modo de ejemplo, se agregaría para cosas
     * particulares de la entidad Persona
     **/

}