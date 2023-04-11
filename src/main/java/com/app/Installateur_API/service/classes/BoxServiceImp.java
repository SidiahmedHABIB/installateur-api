package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.repository.BoxRepository;
import com.app.Installateur_API.service.interfaces.IBoxService;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class BoxServiceImp implements IBoxService {
    @Autowired
    BoxRepository boxRepository;
    @Autowired
    ICompanyService iCompanyService;
    @Override
    public Box creatNewBox(Box box) {
        Box newBox = new Box();
        newBox.setName(box.getName());
        newBox.setEntity(box.getEntity());
        newBox.setMatricul(box.getMatricul());
        newBox.setStatus("NOTINSTALLED");
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
    public Box getBoxById(Long id) {
        return boxRepository.findById(id).get();
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
        newBox.setNserie(box.getNserie());
        newBox.setCompanyBox(box.getCompanyBox());
        newBox.setReportBox(box.getReportBox());
        newBox.setCreatAt(box.getCreatAt());
        newBox.setUpdateAt(new Date());
        return boxRepository.save(newBox);
    }
}
