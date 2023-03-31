package com.app.Installateur_API.service.classes;


import com.app.Installateur_API.entity.ImageData;
import com.app.Installateur_API.repository.StorageRepository;
import com.app.Installateur_API.util.ImageUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class StorageService {

    @Autowired
    private StorageRepository repository;

    public String uploadImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(UUID.randomUUID().toString())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .creatAt(new Date())
                .updateAt(new Date())
                .build());
        if (imageData != null) {
            return "image uploaded successfully : " + imageData.getName();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images= ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
