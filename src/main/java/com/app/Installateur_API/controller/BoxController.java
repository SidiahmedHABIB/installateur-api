package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.ImageData;
import com.app.Installateur_API.entity.PageBox;
import com.app.Installateur_API.entity.PageImage;
import com.app.Installateur_API.service.interfaces.IBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @GetMapping("/boxImages/{id}")
    public ResponseEntity<PageImage> getBoxImages(@PathVariable Long id){
        PageImage boxImage = iBoxService.getBoxImages(id);
        return ResponseEntity.ok().body(boxImage);
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
    @PostMapping("/upload-images")
    public ResponseEntity<String> boxUploadImages(@RequestParam("image1") MultipartFile file1,
                                             @RequestParam("image2") MultipartFile file2,
                                             @RequestParam("id") Long id) throws IOException {
        String uploadImages = iBoxService.boxUploadImages(file1,file2,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImages);
    }
}
