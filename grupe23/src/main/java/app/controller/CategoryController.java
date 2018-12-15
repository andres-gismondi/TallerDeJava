package app.controller;

import app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.service.CategoryService;

@RestController
@RequestMapping(value="/category-controller")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<String> createCategory(@RequestBody Category category,@RequestHeader("Authorization") String token){
        String body = categoryService.createCategory(category,token);
        return UtilsController.getResponseByString(body);
    }

}
