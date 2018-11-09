package dao;

import model.Category;

import javax.persistence.Query;

public class CategoryDAOHibernateJPA extends GenericDAOHibernateJPA<Category> implements CategoryDAO{
    public CategoryDAOHibernateJPA(){
        super(Category.class);
    }

    public Category getByName(String name){
        Query consulta = EMF.getEMF().createEntityManager().createQuery("select c  from " + getPersistentClass().getSimpleName()+ " c where c.name=:name");
        consulta.setParameter("name", name);

        return (Category) consulta.setMaxResults(1).getResultList().get(0);
    }
}
