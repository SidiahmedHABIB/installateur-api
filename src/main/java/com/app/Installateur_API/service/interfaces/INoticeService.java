package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Notice;
import com.app.Installateur_API.entity.PageNotice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INoticeService {
    public String uploadNotice(MultipartFile file) throws Exception;
    public PageNotice getPageNotice(int page, int size);
    public Notice downloadNotice(String fileName);

    }
