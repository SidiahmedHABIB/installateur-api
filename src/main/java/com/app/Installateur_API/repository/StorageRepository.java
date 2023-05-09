package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.ImageData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
    Page<ImageData> findByBox(Box box, Pageable pageable);
    Optional<ImageData> findByBox(Box box);
    void deleteByBox(Box box);

}
