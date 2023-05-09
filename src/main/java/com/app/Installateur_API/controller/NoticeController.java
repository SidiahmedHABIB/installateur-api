package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Notice;
import com.app.Installateur_API.entity.page.PageNotice;
import com.app.Installateur_API.service.interfaces.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService iNoticeService;
    @GetMapping("/page/{page}&{size}")
    public ResponseEntity<PageNotice> getPageNotices(@PathVariable int page, @PathVariable int size){
        PageNotice notices = iNoticeService.getPageNotice(page, size);
        return ResponseEntity.ok().body(notices);
    }
    @PostMapping("/upload/")
    public ResponseEntity<?> uploadImage(@RequestParam("notice") MultipartFile file) throws Exception {
        String uploadNotice = iNoticeService.uploadNotice(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadNotice);
    }

   /*@GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadNotice(@PathVariable String fileName){
        Notice noticeData=iNoticeService.downloadNotice(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(noticeData.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + noticeData.getName()
                        + "\"").body(noticeData.getNoticeData());

    }*/
   @GetMapping("/download/{fileName}")
   public ResponseEntity<byte[]> downloadNotice(@PathVariable String fileName){
       Notice noticeData=iNoticeService.downloadNotice(fileName);
       HttpHeaders headers = new HttpHeaders();
       headers.setContentType(MediaType.parseMediaType(noticeData.getType()));
       headers.setContentDispositionFormData(noticeData.getName(), noticeData.getName());
       headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
       return new ResponseEntity<>(noticeData.getNoticeData(), headers, HttpStatus.OK);
   }
}
