package app.controller;

import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.BillboardService;

import java.util.List;

@RestController
@RequestMapping(value="/billboard-controller")
public class BillboardController {

    @Autowired
    BillboardService billboardService;

    @RequestMapping(value="/set-categories",method = RequestMethod.POST)
    public ResponseEntity<Billboard> setCategories(@RequestBody CategoriesBillboard categoriesBillboard,@RequestHeader("Authorization") String token){
        Billboard billboard = billboardService.setCategories(categoriesBillboard.getCategories(),categoriesBillboard.getBillboard(),token);
        if (billboard != null) {
            return new ResponseEntity<>(billboard,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @RequestMapping(value="/get-publications",method = RequestMethod.POST)
    public ResponseEntity<List<Publication>> getPublications(@RequestBody Billboard billboard,@RequestHeader("Authorization") String token){
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
