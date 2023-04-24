package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Admin;

import java.util.List;

public interface IAdminService {
    public Admin creatNewAdmin(Admin admin);
    public List<Admin> getAllAdmin();
    public Admin getAdminById(Long id);
    public Admin getAdminByEmail(String email);
    public void deletAdmin(Long id);
    public Admin updateAdmin(Admin admin);
    Admin loginAdmin(String email,String password);


}
