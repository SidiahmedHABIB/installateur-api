package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.entity.page.PageBox;
import com.app.Installateur_API.entity.page.PageImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBoxService {
    public Box creatNewBox(Box box,Long id);
    public List<Box> getAllBox();
    public PageBox getPageBoxByStatusAndInter(String status, Long companyId, int page, int size);
    public PageBox getPageBoxByInter(Long companyId, int page, int size);
    public String boxUploadImages(MultipartFile file1, MultipartFile file2, Long id)throws IOException;
    public String boxUploadReport(MultipartFile file, Long id) throws Exception;
    public Box getBoxById(Long id);
    public PageImage getBoxImages(Long id);
    public void deleteBox(Long id);
    public Box upadateBox(Box box,Long id);
    public Box installBox(Box box);
    public Box modifyBox(Box box);
    public Box isSendBox(Long id);
    public boolean unstallBox(Box box);
}
