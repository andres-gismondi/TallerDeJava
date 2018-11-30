package dao;

import model.Billboard;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class BillboardDAOHibernateJPA extends GenericDAOHibernateJPA<Billboard> implements BillboardDAO {
    public BillboardDAOHibernateJPA(){
        super(Billboard.class);
    }

    public Billboard getBillboard(Billboard billboard){
        return this.listar().stream().filter(b -> b.getId()==billboard.getId()).findFirst().orElse(null);

    }
}
