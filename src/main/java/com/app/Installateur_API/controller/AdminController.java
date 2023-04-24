package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Admin;
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

    @PostMapping("/add/")
    public boolean creatNewAdmin(@RequestBody Admin admin){
        adminService.creatNewAdmin(admin);
        return true;
    }

    @PostMapping("/update/")
    public boolean updateAdmin(@RequestBody Admin admin){
        adminService.updateAdmin(admin);
        return true;
    }

    @GetMapping("/delete/{id}")
    public boolean deletByIdAdmin(@PathVariable Long id){
        adminService.deletAdmin(id);
        return true;
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
}
