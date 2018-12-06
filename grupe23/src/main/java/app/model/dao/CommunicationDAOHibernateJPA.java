package app.model.dao;

import app.model.Communication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommunicationDAOHibernateJPA extends GenericDAOHibernateJPA<Communication> implements CommunicationDAO {
    public CommunicationDAOHibernateJPA(){
        super(Communication.class);
    }

    @Override
    @Transactional
    public Communication getCommunication(Communication communication) {

        return this.listar().stream().filter(b -> b.getId()==communication.getId()).findFirst().orElse(null);
    }
}
