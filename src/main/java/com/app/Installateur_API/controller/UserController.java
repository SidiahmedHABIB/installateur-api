package com.app.Installateur_API.controller;


import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = iUserService.getAllUser();
        return ResponseEntity.ok().body(users);
    }
    @PostMapping("/{user}")
    public ResponseEntity<User> creatNewUser(@RequestParam("file") User user){
        User userSaved = iUserService.creatNewUser(user);
        return ResponseEntity.ok().body(userSaved);
    }

}
