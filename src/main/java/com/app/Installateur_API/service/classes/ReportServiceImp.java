package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.Report;
import com.app.Installateur_API.repository.ReportRepository;
import com.app.Installateur_API.service.interfaces.IReportService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;
@Service
@Transactional
public class ReportServiceImp implements IReportService {
    @Autowired
    ReportRepository reportRepository;
    @Override
    public Report uploadReport(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Report report = reportRepository.save(Report.builder()
                    .name(UUID.randomUUID().toString()+".pdf")
                    .type(file.getContentType())
                    .reportData(file.getBytes())
                    .creatAt(new Date())
                    .updateAt(new Date())
                    .build());
            return report;

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Report downloadReport(String fileName) {
        return reportRepository.findByName(fileName).get();
    }
}
