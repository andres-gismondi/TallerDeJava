package app.service;

import app.model.dao.BillboardDAO;
import app.model.dao.CategoryDAO;
import app.model.dao.PublicationDAO;
import app.model.dao.UserDAO;
import app.model.Billboard;
import app.model.Category;
import app.model.Publication;
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

    public Billboard setCategories(List<Category> categories, Billboard billboard, String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getType().equals(UtilsImplementation.ADMIN)) {
                Billboard newBillboard = billboardDAO.getBillboard(billboardDAO.getIdFromBillboard(billboard.getTitle()));
                for (Category cat : categories) {
                    if (!billboardDAO.billboardHasCategory(newBillboard.getTitle(), cat.getName())) {
                        Category category = categoryDAO.getCategory(cat.getName());
                        newBillboard.addCategory(category);
                    }
                }
                newBillboard.setCreator(userDAO.getUser(newBillboard.getCreator().getId()));
                billboardDAO.actualizar(newBillboard);
                return newBillboard;
            }
            return null;
        }
        return null;
    }

    public Billboard getPublications(List<Publication> publications, Billboard billboard, String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            Billboard newBillboard = billboardDAO.getBillboard(billboardDAO.getIdFromBillboard(billboard.getTitle()));
            List<Publication> publications1 = new ArrayList<>();
            for (Publication pub : newBillboard.getPublications()) {
                pub.setCreator(userDAO.getUser(newBillboard.getCreator().getId()));
                publications1.add(publicationDAO.getPublication(pub.getTitle()));
            }
            newBillboard.setPublications(publications1);
            newBillboard.setCreator(userDAO.getUser(newBillboard.getCreator().getId()));
            return newBillboard;
        }
        return null;
    }

}
