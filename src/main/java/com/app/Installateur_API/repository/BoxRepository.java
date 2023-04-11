package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.Box;
import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxRepository extends JpaRepository<Box,Long> {
    Page<Box> findByStatusAndCompanyBox(String status, Company company, Pageable pageable);
    Page<Box> findByCompanyBox(Company company, Pageable pageable);

}
