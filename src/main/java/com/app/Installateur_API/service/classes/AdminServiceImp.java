package com.app.Installateur_API.service.classes;



import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.repository.AdminRepository;
import com.app.Installateur_API.service.interfaces.IAdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        Admin AdminUpdate = new Admin();
        AdminUpdate.setId(admin.getId());
        AdminUpdate.setFirstName(admin.getFirstName());
        AdminUpdate.setLastName(admin.getLastName());
        AdminUpdate.setEmail(admin.getEmail());
        AdminUpdate.setPassword(admin.getPassword());
        AdminUpdate.setUpdateAt(new Date());
        return adminRepository.save(AdminUpdate);
    }

    @Override
    public Admin loginAdmin(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password).get();
    }
}
