package com.app.Installateur_API.repository;

import com.app.Installateur_API.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String email);
    Boolean existsByEmail(String email);
}
