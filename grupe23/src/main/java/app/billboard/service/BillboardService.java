package app.billboard.service;

import app.billboard.repository.BillboardDAO;
import app.category.repository.CategoryDAO;
import app.publication.repository.PublicationDAO;
import app.user.repository.UserDAO;
import app.billboard.model.Billboard;
import app.category.model.Category;
import app.publication.model.Publication;
import app.application.utils.UtilsImplementation;
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

    public List<Publication> getPublications(Billboard billboard, String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            Billboard newBillboard = billboardDAO.getBillboard(billboardDAO.getIdFromBillboard(billboard.getTitle()));
            List<Publication> publications1 = new ArrayList<>();
            for (Publication pub : newBillboard.getPublications()) {
                Publication pp = new Publication();
                pp.setCreator(userDAO.getUser(pub.getCreator().getId()));
                pp.setBody(pub.getBody());
                pp.setDate(pub.getDate());
                pp.setEnableComments(pub.getEnableComments());
                pp.setTitle(pub.getTitle());
                pp.setCommentaries(pub.getCommentaries());
                pp.setId(pub.getId());
                pp.setBillboard(pub.getBillboard());
                publications1.add(pp);
            }

            return publications1;
        }
        return null;
    }

    public List<Billboard> getBillboards(String token) {
        if (token.equals(userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getId() + "-" + UtilsImplementation.TOKEN)) {
            if (userDAO.getUser(UtilsImplementation.getIdFromAuthorizationToken(token)).getType().equals(UtilsImplementation.ADMIN)) {
                return billboardDAO.getBillboards();
            }
        }
        return null;
    }

}
