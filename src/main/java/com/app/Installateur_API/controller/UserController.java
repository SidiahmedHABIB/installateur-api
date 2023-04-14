package com.app.Installateur_API.controller;


import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    @PostMapping("/")
    public ResponseEntity<User> creatNewUser(@RequestBody User user){
        User userSaved = iUserService.creatNewUser(user);
        return ResponseEntity.ok().body(userSaved);
    }
    @PostMapping("/login/")
    public ResponseEntity<Map<String,User>> loginUsers(@RequestParam("email") String email, @RequestParam("password") String password){
        User user = iUserService.loginUser(email,password);
        return ResponseEntity.ok().body(Map.of("user",user));
        //return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/updateProfile")
    public ResponseEntity<User> updateProfile(@RequestParam("image") MultipartFile file,
                                                          @RequestParam("id") Long id)throws IOException {
        User userSaved = iUserService.modifyProfile(id,file);
        return ResponseEntity.ok().body(userSaved);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User users = iUserService.getUserById(id);
        return ResponseEntity.ok().body(users);
    }


}
