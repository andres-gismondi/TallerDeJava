package app.model.dao;

import app.model.Publication;

public interface PublicationDAO extends GenericDAO<Publication> {
    public Publication getPublication(String title);
}
