package app.publication.repository;

import app.application.repository.GenericDAO;
import app.publication.model.Publication;

import java.util.List;

public interface PublicationDAO extends GenericDAO<Publication> {
    public Publication getPublication(String title);

    public List<Publication> getPublications(String title);
}
