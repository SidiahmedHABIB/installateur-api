package com.app.Installateur_API.controller;


import com.app.Installateur_API.entity.LoginRequest;
import com.app.Installateur_API.entity.LoginResponse;
import com.app.Installateur_API.entity.page.PageUser;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/all/{page}&{size}")
    public ResponseEntity<PageUser> getPageAllUser(@PathVariable int page, @PathVariable int size){
        PageUser users = iUserService.getPageAllUser(page,size);
        return ResponseEntity.ok().body(users);
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getPageAllUser(){
        List<User> users = iUserService.getAllUser();
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/add/")
    public ResponseEntity<Boolean> creatNewUser(@RequestBody User user){
        boolean message = iUserService.creatNewUser(user);
        return ResponseEntity.ok().body(message);
    }
    @PostMapping("/updateUser/")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User userSaved = iUserService.modifyUser(user);
        return ResponseEntity.ok().body(userSaved);
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
    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletUserById(@PathVariable Long id){
        boolean result = iUserService.deleteUser(id);
        return ResponseEntity.ok().body(result);
    }


}
