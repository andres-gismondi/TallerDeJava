package dao;

import model.Category;

public interface CategoryDAO extends GenericDAO<Category>{
    public Category getCategory(Category category);
}
