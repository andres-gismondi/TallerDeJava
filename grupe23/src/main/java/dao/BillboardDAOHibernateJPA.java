package dao;

import model.Billboard;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
public class BillboardDAOHibernateJPA extends GenericDAOHibernateJPA<Billboard> implements BillboardDAO {
    public BillboardDAOHibernateJPA(){
        super(Billboard.class);
    }

    @Override
    @Transactional
    public Billboard getBillboard(long id){
        Billboard billboard = this.listar().stream().filter(b -> b.getId()==id).findFirst().orElse(null);
        if(billboard!=null){
            Hibernate.initialize(billboard.getCategories());
            Hibernate.initialize(billboard.getPublications());
        }

        return billboard;
    }

    @Transactional
    public long getIdFromBillboard(String title){
        Billboard billboard = this.listar().stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null);
        if(billboard!=null){
            Hibernate.initialize(billboard.getCategories());
        }

        return billboard.getId();
    }

    @Transactional
    public Billboard getBillboardByTitle(String title){
        Billboard bill = this.listar().stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null);
        if(bill!=null){
            Hibernate.initialize(bill.getCategories());
        }

        return bill;
    }

    public Boolean billboardHasCategory(String title, String name){
        Billboard billboardCategory = this.getBillboardByTitle(title);
        if(billboardCategory!=null){
            if(billboardCategory.getCategories().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null)!=null){
                return true;
            }
        }
        return false;
    }

    public Boolean billboardHasPublication(String title, String name){
        Billboard billboardPublication = this.getBillboardByTitle(title);
        if(billboardPublication!=null){
            if(billboardPublication.getCategories().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null)!=null){
                return true;
            }
        }
        return false;
    }
}
