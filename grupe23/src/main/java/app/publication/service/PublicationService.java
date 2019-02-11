package app.publication.service;

import app.billboard.model.Billboard;
import app.billboard.repository.BillboardDAO;
import app.publication.repository.PublicationDAO;
import app.user.repository.UserDAO;
import app.publication.model.Publication;
import app.user.model.User;
import app.application.utils.UtilsImplementation;
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
