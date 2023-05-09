package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.page.PageCompany;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private ICompanyService iCompanyService;
    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompany(){
        List<Company> company = iCompanyService.getAllCompany();
        return ResponseEntity.ok().body(company);
    }
    @GetMapping("/allPage/{page}&{size}")
    public ResponseEntity<PageCompany> getPageAllCompanies(@PathVariable int page, @PathVariable int size){
        PageCompany companier = iCompanyService.getPageAllCompanier(page,size);
        return ResponseEntity.ok().body(companier);
    }
    @PostMapping("/add/")
    public ResponseEntity<Company> creatNewCompany(@RequestBody Company company){
        Company companySaved = iCompanyService.creatNewCompany(company);
        return ResponseEntity.ok().body(companySaved);
    }
    @PostMapping("/update/")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company){
        Company companyUpdated = iCompanyService.modifyCompany(company);
        return ResponseEntity.ok().body(companyUpdated);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCompanyById(@PathVariable Long id){
        iCompanyService.deleteCompany(id);
        return ResponseEntity.ok().body(true);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = iCompanyService.getCompanyById(id);
        return ResponseEntity.ok().body(company);
    }
}
