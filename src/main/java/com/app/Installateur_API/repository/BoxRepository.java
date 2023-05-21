package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxRepository extends JpaRepository<Box,Long> {
    Page<Box> findByStatusAndInterventionBox(String status, Intervention intervention, Pageable pageable);
    Page<Box> findByInterventionBox(Intervention intervention, Pageable pageable);

}
