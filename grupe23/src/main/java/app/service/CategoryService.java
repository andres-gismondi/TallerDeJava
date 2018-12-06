package app.service;

import app.model.dao.CategoryDAO;
import app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public Boolean createCategory(Category category){
        Category cat = new Category();
        cat.setName(category.getName());
        cat.setWritePermisson(category.getWritePermisson());
        categoryDAO.persistir(cat);
        return true;
    }

}
