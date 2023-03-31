package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Notice;
import org.springframework.web.multipart.MultipartFile;

public interface INoticeService {
    public String uploadNotice(MultipartFile file) throws Exception;
    public Notice downloadNotice(String fileName);

    }
