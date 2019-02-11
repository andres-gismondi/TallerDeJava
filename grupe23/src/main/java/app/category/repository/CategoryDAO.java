package app.category.repository;

import app.application.repository.GenericDAO;
import app.category.model.Category;

import java.util.List;

public interface CategoryDAO extends GenericDAO<Category> {
    public Category getCategory(String name);

    public List<Category> getCategories();
}
