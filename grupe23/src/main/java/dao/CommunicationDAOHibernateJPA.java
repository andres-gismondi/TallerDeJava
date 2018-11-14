package dao;

import model.Communication;

public class CommunicationDAOHibernateJPA extends GenericDAOHibernateJPA<Communication> implements CommunicationDAO {
    public CommunicationDAOHibernateJPA(){
        super(Communication.class);
    }

    @Override
    public Communication getCommunication(Communication communication) {

        return this.listar().stream().filter(b -> b.getId()==communication.getId()).findFirst().orElse(null);
    }
}
