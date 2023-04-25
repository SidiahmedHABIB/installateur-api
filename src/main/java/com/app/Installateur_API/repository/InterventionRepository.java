package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.ImageData;
import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterventionRepository extends JpaRepository<Intervention,Long> {
    Page<Intervention> findByStatus(String status, Pageable pageable);
    Page<Intervention> findByUserAndStatus(User user,String status, Pageable pageable);
    Page<Intervention> findByUser(User user, Pageable pageable);
    Page<Intervention> findByCompany(Company company, Pageable pageable);

}
