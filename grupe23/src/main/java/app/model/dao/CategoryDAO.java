package app.model.dao;

import app.model.Category;

public interface CategoryDAO extends GenericDAO<Category>{
    public Category getCategory(String name);
}
