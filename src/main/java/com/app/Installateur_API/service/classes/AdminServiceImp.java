package com.app.Installateur_API.service.classes;



import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.entity.page.PageAdmin;
import com.app.Installateur_API.repository.AdminRepository;
import com.app.Installateur_API.service.interfaces.IAdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class AdminServiceImp implements IAdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin creatNewAdmin(Admin admin) {
        return adminRepository.save(admin);
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
    public Admin loginAdmin(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password).get();
    }
    @Scheduled(fixedDelay = 1000 * 1) // Run every 5 minutes
    void prinInfo(){
        System.out.println("print info method ");
    }
}
