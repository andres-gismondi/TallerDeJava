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

    public EntityManager getEntityManager() {
        return entityManager;
    }



    @Override
    public T persistir(T entity) {
        this.getEntityManager().persist(entity);
        return entity;
    }

    public T actualizar(T entity) {
        this.getEntityManager().merge(entity);
        return entity;
    }

    @Override
    public void borrar(T entity) {
        this.getEntityManager().remove(this.entityManager.contains(entity) ? entity : this.getEntityManager().merge(entity));
    }

    public T borrar(Serializable id) {
        T entity = this.getEntityManager().find(this.getPersistentClass(),id);
        return entity;
    }

    public List<T> recuperarTodos(String columnOrder) {
        Query consulta = this.getEntityManager().createQuery("from " + getPersistentClass().getSimpleName()+ " e order by e."+ columnOrder);
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public List<T> listar() {
        Query consulta = this.getEntityManager().createQuery("from "+getPersistentClass().getSimpleName()+" e");
        List<T> resultado = (List<T>) consulta.getResultList();
        return resultado;
    }

    public boolean existe(Serializable id){
        Query consulta = this.getEntityManager().createQuery("from " +getPersistentClass().getSimpleName()+" e where e.id=:id");
        consulta.setParameter("id", id);
        return (consulta.getResultList().size() == 1);

    }

    public T recuperar(Serializable id){
        T entity = this.getEntityManager().find(this.getPersistentClass(),id);
        return entity;
    }
}