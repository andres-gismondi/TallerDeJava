package app.publication.repository;

import app.application.repository.GenericDAO;
import app.publication.model.Communication;

public interface CommunicationDAO extends GenericDAO<Communication> {

    public Communication getCommunication(Communication communication);

}
