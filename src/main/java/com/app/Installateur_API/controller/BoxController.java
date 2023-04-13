package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.PageBox;
import com.app.Installateur_API.service.interfaces.IBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {
    @Autowired
    private IBoxService iBoxService;
    @GetMapping("/all")
    public ResponseEntity<List<Box>> getAllBox(){
        List<Box> boxs = iBoxService.getAllBox();
        return ResponseEntity.ok().body(boxs);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Box> getBoxbyId(@PathVariable Long id){
        Box box = iBoxService.getBoxById(id);
        return ResponseEntity.ok().body(box);
    }
    @GetMapping("/pageAll/{companyId}&{page}&{size}")
    public ResponseEntity<PageBox> getPageBoxByCompany(@PathVariable Long companyId, @PathVariable int page, @PathVariable int size){
        PageBox pageBox = iBoxService.getPageBoxByCompany(companyId,page, size);
        return ResponseEntity.ok().body(pageBox);
    }
    @GetMapping("/pageStatus/{companyId}&{status}&{page}&{size}")
    public ResponseEntity<PageBox> getPageBoxByStatusAndCompany(@PathVariable Long companyId,@PathVariable String status, @PathVariable int page, @PathVariable int size){
        PageBox pageBox = iBoxService.getPageBoxByStatusAndCompany(status,companyId,page, size);
        return ResponseEntity.ok().body(pageBox);
    }
}
