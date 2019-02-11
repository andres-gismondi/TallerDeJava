package app.publication.controller;

import app.application.controller.*;
import app.publication.controller.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.publication.service.PublicationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/publication-controller")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<String> createPublication(@RequestBody HttpResponsePublication httpResponsePublication, @RequestHeader("Authorization") String token){
        String body = publicationService.createPublication(httpResponsePublication.getPublication(), httpResponsePublication.getUser(), httpResponsePublication.getBillboard(),token);
        return UtilsController.getResponseByString(body);
    }

}
