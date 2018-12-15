package app.controller;

import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.BillboardService;

@RestController
@RequestMapping(value="/billboard-controller")
public class BillboardController {

    @Autowired
    BillboardService billboardService;

    @RequestMapping(value="/set-categories",method = RequestMethod.POST)
    public ResponseEntity<Billboard> setCategories(@RequestBody CategoriesBillboard categoriesBillboard,@RequestHeader("Authorization") String token){
        Billboard billboard = billboardService.setCategories(categoriesBillboard.getCategories(),categoriesBillboard.getBillboard(),token);
        if (billboard != null) {
            return new ResponseEntity<Billboard>(billboard,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value="/get-publications",method = RequestMethod.POST)
    public ResponseEntity<Billboard> getPublications(@RequestBody PublicationsBillboard publicationsBillboard,@RequestHeader("Authorization") String token){
        Billboard billboard = billboardService.getPublications(publicationsBillboard.getPublications(),publicationsBillboard.getBillboard(),token);
        if (billboard != null) {
            return new ResponseEntity<Billboard>(billboard,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
