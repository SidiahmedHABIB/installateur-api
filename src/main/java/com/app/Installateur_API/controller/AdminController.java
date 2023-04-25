package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.entity.PageAdmin;
import com.app.Installateur_API.entity.PageUser;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @GetMapping()
    public List<Admin> getAllAdmins(){
        return adminService.getAllAdmin();

    }
    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id);

    }

    /*@GetMapping("/login/{email}&{pass}")
    public Admin loginAdmin(@PathVariable String email, @PathVariable String pass){
        return adminService.loginAdmin(email, pass);
    }*/
    @GetMapping("/login/{email}&{password}")
    public ResponseEntity<Admin> loginAdmin(@PathVariable String email, @PathVariable String password){
        Admin admin = adminService.loginAdmin(email,password);
        return ResponseEntity.ok().body(admin);
        //return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/all/{page}&{size}")
    public ResponseEntity<PageAdmin> getAllPageAdmin(@PathVariable int page, @PathVariable int size){
        PageAdmin admin = adminService.getAllPageAdmin(page,size);
        return ResponseEntity.ok().body(admin);
    }
    @PostMapping("/add/")
    public ResponseEntity<Admin> creatNewAdmin(@RequestBody Admin admin){
        Admin adminSaved = adminService.creatNewAdmin(admin);
        return ResponseEntity.ok().body(adminSaved);
    }
    @PostMapping("/update/")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin){
        Admin adminUpdated = adminService.updateAdmin(admin);
        return ResponseEntity.ok().body(adminUpdated);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletAdminById(@PathVariable Long id){
        adminService.deletAdmin(id);
        return ResponseEntity.ok().body(true);
    }
}
