package com.app.Installateur_API.security;
import com.app.Installateur_API.entity.AppRole;
import com.app.Installateur_API.entity.AppUser;
import com.app.Installateur_API.repository.AppRoleRepository;
import com.app.Installateur_API.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    public AccountService(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    public AppUser newUser(AppUser appUser){
        return appUserRepository.save(appUser);
    }
    public AppRole newRole(AppRole appRole){
        return appRoleRepository.save(appRole);
    }
    /*public void addRoleToUser(String userName,String roleName){
        AppUser appUser=appUserRepository.findByUsername(userName);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
        appUserRepository.save(appUser);
    }*/
    public AppUser findByEmail(String email){
        return appUserRepository.findByEmail(email);
    }
    public List<AppUser> getAll(){
        return  appUserRepository.findAll();
    }
}
