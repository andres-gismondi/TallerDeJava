package service;

import dao.BillboardDAO;
import dao.PublicationDAO;
import dao.UserDAO;
import model.Billboard;
import model.Publication;
import model.User;
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

    public Boolean createPublication(Publication publication, User user){
        Publication pub = new Publication();
        pub.setBody(publication.getBody());
        pub.setDate(publication.getDate());
        pub.setEnableComments(publication.getEnableComments());
        pub.setTitle(publication.getTitle());

        User u = userDAO.getUserByEmail(user.getEmail());

        pub.setCreator(u);

        publicationDAO.persistir(pub);
        return true;
    }

}
