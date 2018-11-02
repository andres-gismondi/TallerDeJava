package dao;

import model.Communication;

public class CommunicationDAOHibernateJPA extends GenericDAOHibernateJPA<Communication> implements CommunicationDAO {
    public CommunicationDAOHibernateJPA(){
        super(Communication.class);
    }
}
