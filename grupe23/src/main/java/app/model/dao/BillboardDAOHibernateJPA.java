package app.model.dao;

import app.model.Billboard;
import app.model.Publication;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BillboardDAOHibernateJPA extends GenericDAOHibernateJPA<Billboard> implements BillboardDAO {
    public BillboardDAOHibernateJPA(){
        super(Billboard.class);
    }

    @Override
    @Transactional
    public Billboard getBillboard(long id){
        Billboard bill = this.listar().stream().filter(b -> b.getId()==id).findFirst().orElse(null);
        if(bill!=null){
            Hibernate.initialize(bill.getCategories());
            Hibernate.initialize(bill.getPublications());
            Hibernate.initialize(bill.getCreator().getCategories());
            for (Publication pub:bill.getPublications()) {
                Hibernate.initialize(pub.getCommentaries());
            }
        }

        return bill;
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

    @Override
    @Transactional
    public List<Billboard> getBillboards(){
        List<Billboard> billboards = this.listar();
        for (Billboard bill:billboards) {
            Hibernate.initialize(bill.getCategories());
            Hibernate.initialize(bill.getPublications());
            Hibernate.initialize(bill.getCreator().getCategories());
            for (Publication pub:bill.getPublications()) {
                Hibernate.initialize(pub.getCommentaries());
            }
        }
        return billboards;
    }
}
