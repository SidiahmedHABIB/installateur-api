package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.Report;
import org.springframework.web.multipart.MultipartFile;

public interface IReportService {
    public Report uploadReport(MultipartFile file) throws Exception;
    public Report downloadReport(String fileName);
    public boolean deleteReport(Long id);
}
