package app.billboard.controller.http;

import app.billboard.model.Billboard;
import app.publication.model.Publication;

import java.util.List;

public class HttpResponseBillboardPublications {

    private List<Publication> publications;
    private Billboard billboard;

    public HttpResponseBillboardPublications() {
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
