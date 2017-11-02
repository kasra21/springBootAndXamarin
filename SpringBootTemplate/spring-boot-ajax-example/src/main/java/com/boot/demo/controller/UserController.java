package com.boot.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.demo.model.AddUpdateUserCriteria;
import com.boot.demo.model.AjaxResponseBody;
import com.boot.demo.model.SearchOrDeleteCriteria;
import com.boot.demo.model.User;
import com.boot.demo.services.UserService;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchOrDeleteCriteria search, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        List<User> users = userService.findByUserNameOrEmail(search.getUsername());
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/api/addUser")
    public ResponseEntity<?> addUsersResultViaAjax(@Valid @RequestBody AddUpdateUserCriteria add, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        
        List<User> users = userService.addUser(add);
        if (users.isEmpty()) {
            result.setMsg("could not add user");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/api/upadteUser")
    public ResponseEntity<?> updateUsersResultViaAjax(@Valid @RequestBody AddUpdateUserCriteria update, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        
        List<User> users = userService.updateUser(update);
        if (users.isEmpty()) {
            result.setMsg("could not update user");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/api/deleteUser")
    public ResponseEntity<?> deleteUsersResultViaAjax(@Valid @RequestBody SearchOrDeleteCriteria delete, Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();
        
        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }
        
        List<User> users = userService.deleteUser(delete.getUsername());
        if (users.isEmpty()) {
            result.setMsg("could not delete user");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/api/getUsers")
    public ResponseEntity<?> getUsersResultViaAjax() {

        AjaxResponseBody result = new AjaxResponseBody();

        List<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/api/testConnection")
    public ResponseEntity<?> testConnectionResultViaAjax() {

        AjaxResponseBody result = new AjaxResponseBody();

        String success = userService.mysqlConnectTest();
        result.setMsg(success);
        result.setResult(null);

        return ResponseEntity.ok(result);
    }

}
