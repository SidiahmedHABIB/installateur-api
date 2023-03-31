package com.app.Installateur_API.controller;


import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private ICompanyService iCompanyService;
    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> company = iCompanyService.getAllCompany();
        return ResponseEntity.ok().body(company);
    }
}
