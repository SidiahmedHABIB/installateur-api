package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.entity.LoginRequest;
import com.app.Installateur_API.entity.LoginResponseAdmin;
import com.app.Installateur_API.entity.page.PageAdmin;
import com.app.Installateur_API.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
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
   // @CrossOrigin(origins = "*", allowedHeaders = "*")

    @GetMapping("/all/{page}&{size}")
    public ResponseEntity<PageAdmin> getAllPageAdmin(@PathVariable int page, @PathVariable int size){
        PageAdmin admin = adminService.getAllPageAdmin(page,size);
        return ResponseEntity.ok().body(admin);
    }
    @PostMapping("/add/")
    public ResponseEntity<Boolean> creatNewAdmin(@RequestBody Admin admin){
        boolean message = adminService.creatNewAdmin(admin);
        return ResponseEntity.ok().body(message);
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
