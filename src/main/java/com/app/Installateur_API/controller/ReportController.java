package com.app.Installateur_API.controller;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.Report;
import com.app.Installateur_API.service.interfaces.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    IReportService iReportService;
    @PostMapping("/upload")
    public ResponseEntity<Report> uploadImage(@RequestParam("report") MultipartFile file) throws Exception {
        Report uploadNotice = iReportService.uploadReport(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadNotice);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadNotice(@PathVariable String fileName){
        Report reportData =iReportService.downloadReport(fileName);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(reportData.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + reportData.getName()
                                + "\"").body(reportData.getReportData());

    }
}
