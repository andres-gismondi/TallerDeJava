package app.service;

import app.model.dao.BillboardDAO;
import app.model.dao.CategoryDAO;
import app.model.dao.PublicationDAO;
import app.model.dao.UserDAO;
import app.model.Billboard;
import app.model.Category;
import app.model.Publication;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillboardService {

    @Autowired
    BillboardDAO billboardDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    PublicationDAO publicationDAO;

    @Autowired
    UserDAO userDAO;

    public Boolean createBillboard(Billboard billboard,User user){
        Billboard bill = new Billboard();
        bill.setTitle(billboard.getTitle());
        bill.setDescription(billboard.getDescription());
        bill.setDate(billboard.getDate());

        User u = userDAO.getUserByEmail(user.getEmail());

        bill.setCreator(u);

        billboardDAO.persistir(bill);
        return true;
    }

    public Billboard setCategories(List<Category> categories, Billboard billboard){

        Billboard newBillboard = billboardDAO.getBillboard(billboardDAO.getIdFromBillboard(billboard.getTitle()));
        for (Category cat: categories) {
            if(!billboardDAO.billboardHasCategory(newBillboard.getTitle(),cat.getName())){
                Category category = categoryDAO.getCategory(cat.getName());
                newBillboard.addCategory(category);
            }
        }
        newBillboard.setCreator(userDAO.getUser(newBillboard.getCreator().getId()));
        billboardDAO.actualizar(newBillboard);
        return newBillboard;
    }

    public Billboard getPublications(List<Publication> publications, Billboard billboard){
        Billboard newBillboard = billboardDAO.getBillboard(billboardDAO.getIdFromBillboard(billboard.getTitle()));
        List<Publication> publications1 = new ArrayList<>();
        for (Publication pub:newBillboard.getPublications()) {
            publications1.add(publicationDAO.getPublication(pub.getTitle()));
        }
        newBillboard.setPublications(publications1);
        newBillboard.setCreator(userDAO.getUser(newBillboard.getCreator().getId()));
        return newBillboard;
    }

}
