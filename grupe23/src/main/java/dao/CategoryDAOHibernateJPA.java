package dao;

import model.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryDAOHibernateJPA extends GenericDAOHibernateJPA<Category> implements CategoryDAO{
    public CategoryDAOHibernateJPA(){
        super(Category.class);
    }

    public Category getCategory(String name){
        return this.listar().stream().filter(b -> b.getName().equals(name)).findFirst().orElse(null);
    }

}
