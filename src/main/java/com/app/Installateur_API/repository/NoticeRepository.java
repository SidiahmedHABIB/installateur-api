package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
    Optional<Notice> findByName(String fileName);

}
