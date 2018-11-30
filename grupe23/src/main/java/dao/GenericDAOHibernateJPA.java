package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public GenericDAOHibernateJPA(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em){
        this.entityManager = em;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }



    @Override
    public T persistir(T entity) {
        this.getEntityManager().persist(entity);
        return entity;
    }

    public T actualizar(T entity) {
<<<<<<< HEAD
        EntityTransaction etx = this.getEntityManager().getTransaction();
        etx.begin();
        T newEntity = this.getEntityManager().merge(entity);
        etx.commit();
        this.getEntityManager().close();
        return newEntity;
=======
        this.getEntityManager().merge(entity);
        return entity;
>>>>>>> d93cd77bd724978889acfb2b0567cced41690817
    }

    @Override
    public void borrar(T entity) {
<<<<<<< HEAD
        EntityTransaction tx = null;
        try {
            tx = this.getEntityManager().getTransaction();
            tx.begin();
            this.getEntityManager().remove(this.getEntityManager().contains(entity) ? entity : this.getEntityManager().merge(entity));
            //em.remove(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e; // escribir en un log o mostrar un mensaje
        } finally {
            this.getEntityManager().close();
        }
    }

    public T borrar(Serializable id) {
        T entity = this.getEntityManager().find(this.getPersistentClass(), id);
        if (entity != null) {
            this.borrar(entity);
        }
=======
        this.getEntityManager().remove(this.entityManager.contains(entity) ? entity : this.getEntityManager().merge(entity));
    }

    public T borrar(Serializable id) {
        T entity = this.getEntityManager().find(this.getPersistentClass(),id);
>>>>>>> d93cd77bd724978889acfb2b0567cced41690817
        return entity;
    }

    public List<T> recuperarTodos(String columnOrder) {
<<<<<<< HEAD
        Query consulta = this.getEntityManager().createQuery("select e  from " + getPersistentClass().getSimpleName()+" e order by e." + columnOrder);
=======
        Query consulta = this.getEntityManager().createQuery("from " + getPersistentClass().getSimpleName()+ " e order by e."+ columnOrder);
>>>>>>> d93cd77bd724978889acfb2b0567cced41690817
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public List<T> listar() {
<<<<<<< HEAD
        Query consulta = this.getEntityManager().createQuery("select e from " + getPersistentClass().getSimpleName() + " e");
=======
        Query consulta = this.getEntityManager().createQuery("from "+getPersistentClass().getSimpleName()+" e");
>>>>>>> d93cd77bd724978889acfb2b0567cced41690817
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public boolean existe(Serializable id){
<<<<<<< HEAD
        Query consulta = this.getEntityManager().createQuery("select e  from " + getPersistentClass().getSimpleName()+ " e where e.id=:id");
=======
        Query consulta = this.getEntityManager().createQuery("from " +getPersistentClass().getSimpleName()+" e where e.id=:id");
>>>>>>> d93cd77bd724978889acfb2b0567cced41690817
        consulta.setParameter("id", id);
        return (consulta.getResultList().size() == 1);

    }

    public T recuperar(Serializable id){
<<<<<<< HEAD
        T entity = this.getEntityManager().find(this.getPersistentClass(), id);
=======
        T entity = this.getEntityManager().find(this.getPersistentClass(),id);
>>>>>>> d93cd77bd724978889acfb2b0567cced41690817
        return entity;
    }
}