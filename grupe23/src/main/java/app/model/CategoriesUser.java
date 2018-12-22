package app.model;

import java.util.List;

public class CategoriesUser {

    private List<Category> categories;
    private User user;

    public CategoriesUser() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
