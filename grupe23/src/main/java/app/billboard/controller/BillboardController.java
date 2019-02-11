package app.billboard.controller;

import app.billboard.controller.http.HttpResponseBillboardCategories;
import app.billboard.model.Billboard;
import app.publication.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.billboard.service.BillboardService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/billboard-controller")
public class BillboardController {

    @Autowired
    BillboardService billboardService;

    @RequestMapping(value="/set-categories",method = RequestMethod.POST)
    public ResponseEntity<Billboard> setCategories(@RequestBody HttpResponseBillboardCategories httpResponseBillboardCategories, @RequestHeader("Authorization") String token){
        Billboard billboard = billboardService.setCategories(httpResponseBillboardCategories.getCategories(), httpResponseBillboardCategories.getBillboard(),token);
        if (billboard != null) {
            return new ResponseEntity<>(billboard,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value="/get-publications",method = RequestMethod.POST)
    public ResponseEntity<List<Publication>> getPublications(@RequestBody Billboard billboard, @RequestHeader("Authorization") String token){
        List<Publication> publications = billboardService.getPublications(billboard,token);
        if (publications != null) {
            return new ResponseEntity<>(publications,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value="/get-billboards",method = RequestMethod.GET)
    public ResponseEntity<List<Billboard>> getBillboards(@RequestHeader("Authorization") String token){
        List<Billboard> billboards = billboardService.getBillboards(token);
        if (billboards != null) {
            return new ResponseEntity<>(billboards,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.CONFLICT);
    }

}
