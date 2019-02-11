package app.publication.controller.http;

import app.billboard.model.Billboard;
import app.publication.model.Publication;
import app.user.model.User;

public class HttpResponsePublication {

    private Publication publication;
    private User user;
    private Billboard billboard;

    public HttpResponsePublication() {
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
