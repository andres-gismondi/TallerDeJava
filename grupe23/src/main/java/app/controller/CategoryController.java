package app.controller;

import app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.service.CategoryService;

@RestController
@RequestMapping(value="/category-controller")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<Boolean> createCategory(@RequestBody Category category){
        Boolean bb = categoryService.createCategory(category);
        return new ResponseEntity<Boolean>(bb, HttpStatus.OK);
    }

}
