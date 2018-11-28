package dao;

import model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class GenericDAOHibernateJPA<T> implements GenericDAO<T> {
    protected Class<T> persistentClass;
    private EntityManager entityManager;

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
        EntityTransaction etx = this.getEntityManager().getTransaction();
        etx.begin();
        T newEntity = this.getEntityManager().merge(entity);
        etx.commit();
        this.getEntityManager().close();
        return newEntity;
    }

    @Override
    public void borrar(T entity) {
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
        return entity;
    }

    public List<T> recuperarTodos(String columnOrder) {
        Query consulta = this.getEntityManager().createQuery("select e  from " + getPersistentClass().getSimpleName()+" e order by e." + columnOrder);
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public List<T> listar() {
        Query consulta = this.getEntityManager().createQuery("select e from " + getPersistentClass().getSimpleName() + " e");
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public boolean existe(Serializable id){
        Query consulta = this.getEntityManager().createQuery("select e  from " + getPersistentClass().getSimpleName()+ " e where e.id=:id");
        consulta.setParameter("id", id);
        return (consulta.getResultList().size() == 1);

    }

    public T recuperar(Serializable id){
        T entity = this.getEntityManager().find(this.getPersistentClass(), id);
        return entity;
    }
}