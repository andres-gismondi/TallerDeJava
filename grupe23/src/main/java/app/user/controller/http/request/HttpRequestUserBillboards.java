package app.user.controller.http.request;

import app.billboard.model.Billboard;
import app.user.model.User;

public class HttpRequestUserBillboards {

    private Billboard billboard;
    private User user;

    public HttpRequestUserBillboards() {
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
