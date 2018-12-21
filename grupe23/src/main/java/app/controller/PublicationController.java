package app.controller;

import app.model.PublicationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.PublicationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/publication-controller")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<String> createPublication(@RequestBody PublicationBody publicationBody,@RequestHeader("Authorization") String token){
        String body = publicationService.createPublication(publicationBody.getPublication(),publicationBody.getUser(), publicationBody.getBillboard(),token);
        return UtilsController.getResponseByString(body);
    }

}
