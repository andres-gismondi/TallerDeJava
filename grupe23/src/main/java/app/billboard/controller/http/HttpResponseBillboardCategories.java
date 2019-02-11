package app.billboard.controller.http;

import app.billboard.model.Billboard;
import app.category.model.Category;

import java.util.List;

public class HttpResponseBillboardCategories {

    private List<Category> categories;
    private Billboard billboard;

    public HttpResponseBillboardCategories() {
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
