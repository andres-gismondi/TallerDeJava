package app.controller;

import app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/category-controller")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category,@RequestHeader("Authorization") String token){
        String body = categoryService.createCategory(category,token);
        return UtilsController.getResponseByString(body);
    }

    @RequestMapping(value="/get-categories",method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategories(@RequestHeader("Authorization") String token){
        List<Category> categories = categoryService.getCategories(token);
        if (categories != null) {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.CONFLICT);
    }



}
