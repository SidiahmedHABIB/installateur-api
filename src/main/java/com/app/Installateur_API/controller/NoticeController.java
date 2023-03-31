package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Notice;
import com.app.Installateur_API.service.interfaces.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService iNoticeService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("notice") MultipartFile file) throws Exception {
        String uploadNotice = iNoticeService.uploadNotice(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadNotice);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadNotice(@PathVariable String fileName){
        Notice noticeData=iNoticeService.downloadNotice(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(noticeData.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + noticeData.getName()
                        + "\"").body(noticeData.getNoticeData());

    }
}
