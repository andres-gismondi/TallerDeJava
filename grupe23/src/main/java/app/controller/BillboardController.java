package app.controller;

import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.service.BillboardService;

@RestController
@RequestMapping(value="/billboard-controller")
public class BillboardController {

    @Autowired
    BillboardService billboardService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<Boolean> createBillboard(@RequestBody UserBillboards userBillboards){
        Boolean bb = billboardService.createBillboard(userBillboards.getBillboard(),userBillboards.getUser());
        return new ResponseEntity<Boolean>(bb, HttpStatus.OK);
    }

    @RequestMapping(value="/set-categories",method = RequestMethod.POST)
    public ResponseEntity<Billboard> setCategories(@RequestBody CategoriesBillboard categoriesBillboard){
        Billboard billboard = billboardService.setCategories(categoriesBillboard.getCategories(),categoriesBillboard.getBillboard());
        return new ResponseEntity<Billboard>(billboard,HttpStatus.OK);
    }

    @RequestMapping(value="/set-publications",method = RequestMethod.POST)
    public ResponseEntity<Billboard> setPublications(@RequestBody PublicationsBillboard publicationsBillboard){
        Billboard billboard = billboardService.getPublications(publicationsBillboard.getPublications(),publicationsBillboard.getBillboard());
        return new ResponseEntity<Billboard>(billboard,HttpStatus.OK);
    }

}
