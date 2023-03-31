package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report,Long> {
    Optional<Report> findByName(String fileName);

}
