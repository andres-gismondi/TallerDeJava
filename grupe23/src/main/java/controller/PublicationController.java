package controller;

import model.Publication;
import model.PublicationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.PublicationService;

@RestController
@RequestMapping(value="/publication-controller")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<Boolean> createPublication(@RequestBody PublicationBody publicationBody){
        Boolean bb = publicationService.createPublication(publicationBody.getPublication(),publicationBody.getUser());
        return new ResponseEntity<Boolean>(bb, HttpStatus.OK);
    }

}
