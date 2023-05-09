package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Admin;
import com.app.Installateur_API.entity.LoginResponse;
import com.app.Installateur_API.entity.LoginResponseAdmin;
import com.app.Installateur_API.entity.page.PageAdmin;

import java.util.List;

public interface IAdminService {
    public String creatNewAdmin(Admin admin);
    public List<Admin> getAllAdmin();
    public PageAdmin getAllPageAdmin(int page, int size);
    public Admin getAdminById(Long id);
    public Admin getAdminByEmail(String email);
    public void deletAdmin(Long id);
    public Admin updateAdmin(Admin admin);
    public LoginResponseAdmin loginAdmin(String email, String password);


}
