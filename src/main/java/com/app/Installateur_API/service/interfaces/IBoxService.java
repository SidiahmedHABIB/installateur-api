package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Box;

import java.util.List;

public interface IBoxService {
    public Box creatNewBox(Box box);
    public List<Box> getAllBox();
    public Box getBoxById(Long id);
    public void deleteBox(Long id);
    public Box modifyBox(Box box);
}
