package app.service;

import app.model.Billboard;
import app.model.dao.BillboardDAO;
import app.model.dao.PublicationDAO;
import app.model.dao.UserDAO;
import app.model.Publication;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    @Autowired
    PublicationDAO publicationDAO;

    @Autowired
    BillboardDAO billboardDAO;

    @Autowired
    UserDAO userDAO;

    public String createPublication(Publication publication, User user, Billboard billboard, String token) {
        if (token.equals(userDAO.getUserByEmail(user.getEmail()).getId() + "-" + UtilsImplementation.TOKEN)) {
            Publication pub = new Publication();
            pub.setBody(publication.getBody());
            pub.setDate(publication.getDate());
            pub.setEnableComments(publication.getEnableComments());
            pub.setTitle(publication.getTitle());

            User u = userDAO.getUserByEmail(user.getEmail());
            Billboard b = billboardDAO.getBillboardByTitle(billboard.getTitle());

            pub.setCreator(u);
            pub.setBillboard(b);

            publicationDAO.persistir(pub);
            return UtilsImplementation.SUCCESS;
        }
        return UtilsImplementation.ACCESS_DENIED;
    }

}
