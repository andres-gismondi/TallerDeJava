package app.model.dao;

import app.model.Category;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category>{
    public Category getCategory(String name);

    public List<Category> getCategories();
}
