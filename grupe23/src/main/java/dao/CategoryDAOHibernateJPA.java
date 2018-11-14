package dao;

import model.Category;

import javax.persistence.Query;

public class CategoryDAOHibernateJPA extends GenericDAOHibernateJPA<Category> implements CategoryDAO{
    public CategoryDAOHibernateJPA(){
        super(Category.class);
    }

    public Category getCategory(Category category){
        return this.listar().stream().filter(b -> b.getId()==category.getId()).findFirst().orElse(null);
    }

}
