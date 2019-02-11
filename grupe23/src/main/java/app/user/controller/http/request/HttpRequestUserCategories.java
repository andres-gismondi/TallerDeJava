package app.user.controller.http.request;

import app.category.model.Category;
import app.user.model.User;

import java.util.List;

public class HttpRequestUserCategories {

    private List<Category> categories;
    private User user;

    public HttpRequestUserCategories() {
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
