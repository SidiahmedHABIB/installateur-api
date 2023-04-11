package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.PageBox;
import com.app.Installateur_API.entity.PageIntervention;

import java.util.List;

public interface IBoxService {
    public Box creatNewBox(Box box);
    public List<Box> getAllBox();
    public PageBox getPageBoxByStatusAndCompany(String status,Long companyId, int page, int size);
    public PageBox getPageBoxByCompany(Long companyId, int page, int size);

    public Box getBoxById(Long id);
    public void deleteBox(Long id);
    public Box modifyBox(Box box);
}
