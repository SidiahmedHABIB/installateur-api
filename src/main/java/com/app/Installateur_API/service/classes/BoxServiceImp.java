package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.repository.BoxRepository;
import com.app.Installateur_API.service.interfaces.IBoxService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class BoxServiceImp implements IBoxService {
    @Autowired
    BoxRepository boxRepository;
    @Override
    public Box creatNewBox(Box box) {
        return boxRepository.save(box);
    }

    @Override
    public List<Box> getAllBox() {
        return boxRepository.findAll();
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
        return null;
    }
}
