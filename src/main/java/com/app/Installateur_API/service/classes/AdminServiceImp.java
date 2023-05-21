package com.app.Installateur_API.service.classes;



import com.app.Installateur_API.entity.*;
import com.app.Installateur_API.entity.page.PageAdmin;
import com.app.Installateur_API.repository.AdminRepository;
import com.app.Installateur_API.repository.AppRoleRepository;
import com.app.Installateur_API.repository.AppUserRepository;
import com.app.Installateur_API.service.interfaces.IAdminService;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class AdminServiceImp implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public boolean creatNewAdmin(Admin admin) {
        if(appUserRepository.existsByEmail(admin.getEmail())){
            return false;
        }
        List<AppRole> allRole = appRoleRepository.findAll();
        AppUser newUser = new AppUser();
        newUser.setEmail(admin.getEmail());
        newUser.setPassword(encoder.encode(admin.getPassword()));
        newUser.setAppRoles(allRole);
        appUserRepository.save(newUser);
        adminRepository.save(admin);
        return  true;
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    @Override
    public PageAdmin getAllPageAdmin(int page, int size) {
        PageAdmin p2= new PageAdmin();
        Page<Admin> adminPage = adminRepository.findAll(PageRequest.of(page, size));
        p2.setAdmins(adminPage.getContent());
        p2.setTotalPages(adminPage.getTotalPages());
        return  p2;
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByEmail(email).get();
    }

    @Override
    public void deletAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        admin.setUpdateAt(new Date());
        return adminRepository.save(admin);
    }

    @Override
    public Admin loginAdmin(String email) {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        return admin;
    }
    @Scheduled(fixedDelay = 1000 * 1) // Run every 5 minutes
    void prinInfo(){
        System.out.println("print info method ");
    }
}
