package dao;

import model.Communication;

public interface CommunicationDAO extends GenericDAO<Communication>{

    public Communication getCommunication(Communication communication);

}
