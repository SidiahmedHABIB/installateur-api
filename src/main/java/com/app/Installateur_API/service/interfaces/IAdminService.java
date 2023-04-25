package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.entity.PageAdmin;

import java.util.List;

public interface IAdminService {
    public Admin creatNewAdmin(Admin admin);
    public List<Admin> getAllAdmin();
    public PageAdmin getAllPageAdmin(int page, int size);
    public Admin getAdminById(Long id);
    public Admin getAdminByEmail(String email);
    public void deletAdmin(Long id);
    public Admin updateAdmin(Admin admin);
    Admin loginAdmin(String email,String password);


}
