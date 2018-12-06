package model;

public class PublicationBody {

    private Publication publication;
    private User user;

    public PublicationBody() {
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
