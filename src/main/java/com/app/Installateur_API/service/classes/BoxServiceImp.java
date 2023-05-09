package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.entity.page.PageBox;
import com.app.Installateur_API.entity.page.PageImage;
import com.app.Installateur_API.repository.BoxRepository;
import com.app.Installateur_API.repository.StorageRepository;
import com.app.Installateur_API.service.interfaces.IBoxService;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import com.app.Installateur_API.service.interfaces.IReportService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class BoxServiceImp implements IBoxService {

    @Autowired
    BoxRepository boxRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    ICompanyService iCompanyService;
    @Autowired
    StorageService storageService;
    @Autowired
    IReportService iReportService;
    @Override
    public Box creatNewBox(Box box,Long companyId) {
        Company company = iCompanyService.getCompanyById(companyId);
        box.setCompanyBox(company);
        box.setIsSend(false);
        box.setCreatAt(new Date());
        box.setUpdateAt(new Date());
        return boxRepository.save(box);
    }

    @Override
    public List<Box> getAllBox() {
        return boxRepository.findAll();
    }

    @Override
    public PageBox getPageBoxByStatusAndCompany(String status, Long companyId, int page, int size) {
        Company company = iCompanyService.getCompanyById(companyId);
        PageBox pb= new PageBox();
        Page<Box> boxPage = boxRepository.findByStatusAndCompanyBox(status, company,PageRequest.of(page, size));
        pb.setBoxes(boxPage.getContent());
        pb.setTotalPages(boxPage.getTotalPages());
        return pb;
    }

    @Override
    public PageBox getPageBoxByCompany(Long companyId, int page, int size) {
        Company company = iCompanyService.getCompanyById(companyId);
        PageBox pb= new PageBox();
        Page<Box> boxPage = boxRepository.findByCompanyBox(company,PageRequest.of(page, size));
        pb.setBoxes(boxPage.getContent());
        pb.setTotalPages(boxPage.getTotalPages());
        return pb;
    }



    @Override
    public String boxUploadImages(MultipartFile file1, MultipartFile file2, Long id)throws IOException {
        Box box = getBoxById(id);
        ImageData image1 = storageService.boxUploadImage(file1,box);
        ImageData image2 = storageService.boxUploadImage(file2,box);
        if (image1 != null&&image2 != null) {
            return "success";
        }
        return null;
    }

    @Override
    public String boxUploadReport(MultipartFile file, Long id) throws Exception {
        Box box = getBoxById(id);
        Report report = iReportService.uploadReport(file);
        if (report != null) {
            box.setReportBox(report);
            upadateBoxforReport(box);
            return "success";
        }
        return null;
    }

    private void upadateBoxforReport(Box box) {
        box.setUpdateAt(new Date());
        boxRepository.save(box);
    }

    @Override
    public Box getBoxById(Long id) {
        return boxRepository.findById(id).get();
    }

    @Override
    public PageImage getBoxImages(Long id) {
        Box box = getBoxById(id);
        PageImage pageImage = new PageImage();
        Page<ImageData> imagePage =storageRepository.findByBox(box,PageRequest.of(0, 2));
        pageImage.setImages(imagePage.getContent());
        pageImage.setTotalPages(imagePage.getTotalPages());
        return pageImage;
    }

    @Override
    public void deleteBox(Long id) {
        boxRepository.deleteById(id);

    }

    @Override
    public Box upadateBox(Box box,Long companyId) {
        Company company = iCompanyService.getCompanyById(companyId);
        box.setCompanyBox(company);
        box.setUpdateAt(new Date());
        return boxRepository.save(box);
    }
    @Override
    public Box installBox(Box box) {
        box.setStatus("INSTALLED");
        box.setUpdateAt(new Date());
        return boxRepository.save(box);
    }
    @Override
    public boolean unstallBox(Box box) {
        if(box.getReportBox()!=null){
            boolean reportDeleted= iReportService.deleteReport(box.getReportBox().getId());
            boolean imageDeleted= storageService.deleteImageByBox(box);
            if(reportDeleted==false&&imageDeleted==false){
                box.setMatricul(null);
                box.setReportBox(null);
                box.setBoxValue(null);
                box.setIsSend(false);
                box.setStatus("NOTINSTALLED");
                box.setUpdateAt(new Date());
                boxRepository.save(box);
                return true;
            }else {
                return false;
            }
        }else {
            boolean imageDeleted= storageService.deleteImageByBox(box);
            if(imageDeleted==false){
                box.setMatricul(null);
                box.setReportBox(null);
                box.setBoxValue(null);
                box.setIsSend(null);
                box.setStatus("NOTINSTALLED");
                box.setUpdateAt(new Date());
                boxRepository.save(box);
                return true;
            }else {
                return false;
            }
        }

    }

    @Override
    public Box modifyBox(Box box) {
        return boxRepository.save(box);
    }

    @Override
    public Box isSendBox(Long id) {
        Box box = getBoxById(id);
        box.setIsSend(true);
        box.setUpdateAt(new Date());
        return boxRepository.save(box);
    }


}
