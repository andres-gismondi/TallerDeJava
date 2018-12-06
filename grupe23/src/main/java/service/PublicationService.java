package service;

import dao.PublicationDAO;
import model.Billboard;
import model.Publication;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {

    @Autowired
    PublicationDAO publicationDAO;

    public Boolean createPublication(Publication publication, Billboard billboard, User user){
        Publication pub = new Publication();
        pub.setBody(publication.getBody());
        pub.setDate(publication.getDate());
        pub.setEnableComments(publication.getEnableComments());
        pub.setTitle(publication.getTitle());
        pub.setBillboard(billboard);
        pub.setCreator(user);

        publicationDAO.persistir(pub);
        return true;
    }

}
