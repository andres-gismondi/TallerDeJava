package app.model.dao;

import app.model.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryDAOHibernateJPA extends GenericDAOHibernateJPA<Category> implements CategoryDAO{
    public CategoryDAOHibernateJPA(){
        super(Category.class);
    }

    @Override
    @Transactional
    public Category getCategory(String name){
        return this.listar().stream().filter(b -> b.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    @Transactional
    public List<Category> getCategories(){
        List<Category> categories = this.listar();
        return categories;
    }

}
