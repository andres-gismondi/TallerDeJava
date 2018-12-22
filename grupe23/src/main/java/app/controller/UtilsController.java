package app.controller;

import app.service.UtilsImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UtilsController {

    public static ResponseEntity<String> getResponseByString(String response){
        if(UtilsImplementation.SUCCESS.equals(response)){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else if(UtilsImplementation.PERMISSON_DENIED.equals(response)){
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
    }

}
