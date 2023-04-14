package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.repository.BoxRepository;
import com.app.Installateur_API.repository.StorageRepository;
import com.app.Installateur_API.service.interfaces.IBoxService;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import jakarta.transaction.Transactional;
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
    @Override
    public Box creatNewBox(Box box) {
        Box newBox = new Box();
        newBox.setName(box.getName());
        newBox.setEntity(box.getEntity());
        newBox.setMatricul(box.getMatricul());
        newBox.setStatus("NOTINSTALLED");
        newBox.setBoxValue(box.getBoxValue());
        newBox.setNserie(box.getNserie());
        newBox.setCompanyBox(box.getCompanyBox());
        newBox.setReportBox(null);
        newBox.setCreatAt(new Date());
        newBox.setUpdateAt(new Date());
        return boxRepository.save(newBox);
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
        if (image1 != null&&image1 != null) {
            return "success";
        }
        return null;
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

    }

    @Override
    public Box modifyBox(Box box) {
        Box newBox = new Box();
        newBox.setId(box.getId());
        newBox.setEntity(box.getEntity());
        newBox.setMatricul(box.getMatricul());
        newBox.setStatus("INSTALLED");
        newBox.setBoxValue(box.getBoxValue());
        newBox.setNserie(box.getNserie());
        newBox.setCompanyBox(box.getCompanyBox());
        newBox.setReportBox(box.getReportBox());
        newBox.setCreatAt(box.getCreatAt());
        newBox.setUpdateAt(new Date());
        return boxRepository.save(newBox);
    }
}
