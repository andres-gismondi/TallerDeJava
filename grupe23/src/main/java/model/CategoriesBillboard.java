package model;

import java.util.List;

public class CategoriesBillboard {

    private List<Category> categories;
    private Billboard billboard;

    public CategoriesBillboard() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }
}
