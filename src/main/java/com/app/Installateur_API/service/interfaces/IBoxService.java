package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBoxService {
    public Box creatNewBox(Box box);
    public List<Box> getAllBox();
    public PageBox getPageBoxByStatusAndCompany(String status,Long companyId, int page, int size);
    public PageBox getPageBoxByCompany(Long companyId, int page, int size);
    public String boxUploadImages(MultipartFile file1, MultipartFile file2, Long id)throws IOException;
    public String boxUploadReport(MultipartFile file, Long id) throws Exception;
    public Box getBoxById(Long id);
    public PageImage getBoxImages(Long id);
    public void deleteBox(Long id);
    public Box upadateBox(Box box);
    public Box installBox(Box box);
    public Box isSendBox(Long id);
}
