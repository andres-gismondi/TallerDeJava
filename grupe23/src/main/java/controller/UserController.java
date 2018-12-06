package controller;


import dao.CommunicationDAO;
import dao.DaoFactory;
import dao.UserDAO;
import model.CategoriesUser;
import model.Category;
import model.Communication;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import service.UserService;
import sun.misc.IOUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user-controller")
public class UserController {


    @Autowired
    UserService userService;

    public UserController() {
    }

    @RequestMapping(value="/users", method= RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = userService.listAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ResponseEntity<Boolean> createUser(@RequestBody User user){
        Boolean bb = userService.createUser(user);
        return new ResponseEntity<Boolean>(bb,HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") long id,@RequestHeader("token") String token){
        if(token.equals("1234")){
            User user = userService.getUserById(id,token);
            if(user!=null){
                return new ResponseEntity<>(user,HttpStatus.OK);
            }
            return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @RequestMapping(value = "/users/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id, @RequestHeader("token") String token){
        if(userService.deleteUserById(id,token)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }

    @RequestMapping(value="/set-categories",method = RequestMethod.POST)
    public ResponseEntity<User> setCategories(@RequestBody CategoriesUser categoriesUser){
        User user = userService.setCategories(categoriesUser.getCategories(),categoriesUser.getUser());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam("userName") String userName, @RequestParam("password") String password){
        HttpHeaders response = new HttpHeaders();
        HttpHeaders header = userService.loginUser(userName,password,response);
        if(!header.get("token").isEmpty()){
            return ResponseEntity.ok().headers(header).body("ok");
        }
        return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
    }


}