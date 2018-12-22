package app.model;

import java.util.List;

public class PublicationsBillboard {

    private List<Publication> publications;
    private Billboard billboard;

    public PublicationsBillboard() {
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public Billboard getBillboard() {
        return billboard;
    }

    public void setBillboard(Billboard billboard) {
        this.billboard = billboard;
    }
}
