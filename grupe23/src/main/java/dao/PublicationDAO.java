package dao;

import model.Publication;

public interface PublicationDAO extends GenericDAO<Publication> {
    public Publication getPublication(Publication publication);
}
