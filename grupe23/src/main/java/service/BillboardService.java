package service;

import dao.BillboardDAO;
import dao.CategoryDAO;
import dao.PublicationDAO;
import dao.UserDAO;
import model.Billboard;
import model.Category;
import model.Publication;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        billboardDAO.actualizar(newBillboard);
        return newBillboard;
    }

    public Billboard setPublications(List<Publication> publications, Billboard billboard){
        Billboard newBillboard = billboardDAO.getBillboard(billboardDAO.getIdFromBillboard(billboard.getTitle()));
        for (Publication pub: publications) {
            if(!billboardDAO.billboardHasPublication(newBillboard.getTitle(),pub.getTitle())){
                Publication publication = publicationDAO.getPublication(pub.getTitle());
                newBillboard.addPublication(publication);
            }
        }
        billboardDAO.actualizar(newBillboard);
        return newBillboard;
    }

}
