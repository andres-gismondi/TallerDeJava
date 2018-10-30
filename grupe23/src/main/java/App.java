import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("grupo23");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Category c = new Category();
        c.setName("Admin");
        c.setWritePermisson(true);


        User p = new Publisher();
        p.setFirstName("Publisher");
        p.setLastName("Publisher");
        p.setEmail("publisher@publisher");
        p.setType("User");
        p.addCategory(c);

        em.persist(c);
        em.persist(p);

        em.getTransaction().commit();
        em.close();
    }
}


