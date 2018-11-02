package dao;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;
    private EntityManager entityManager =  EMF.getEMF().createEntityManager();


    public GenericDAOHibernateJPA(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }



    @Override
    public T persistir(T entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.persist(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e; // escribir en un log o mostrar un mensaje
        } finally {
            em.close();
        }
        return entity;
    }

    public T actualizar(T entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        T newEntity = em.merge(entity);
        etx.commit();
        em.close();
        return newEntity;
    }

    @Override
    public void borrar(T entity) {
        EntityManager em = EMF.getEMF().createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            //em.remove(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e; // escribir en un log o mostrar un mensaje
        } finally {
            em.close();
        }
    }

    public T borrar(Serializable id) {
        T entity = EMF.getEMF().createEntityManager().find(this.getPersistentClass(), id);
        if (entity != null) {
            this.borrar(entity);
        }
        return entity;
    }

    public List<T> recuperarTodos(String columnOrder) {
        Query consulta = EMF.getEMF().createEntityManager().createQuery("select e  from " + getPersistentClass().getSimpleName()+" e order by e." + columnOrder);
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public List<T> listar() {
        Query consulta = EMF.getEMF().createEntityManager().createQuery("select e from " + getPersistentClass().getSimpleName() + " e");
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public boolean existe(Serializable id){
        return true;
    }

    public T recuperar(Serializable id){
        T entity = EMF.getEMF().createEntityManager().find(this.getPersistentClass(), id);
        return entity;
    }
}