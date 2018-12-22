package app.model.dao;

import app.model.Communication;

public interface CommunicationDAO extends GenericDAO<Communication>{

    public Communication getCommunication(Communication communication);

}
