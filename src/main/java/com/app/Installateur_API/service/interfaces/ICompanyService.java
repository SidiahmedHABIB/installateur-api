package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.page.PageCompany;

import java.util.List;

public interface ICompanyService {
    public Company creatNewCompany(Company company);
    public List<Company> getAllCompany();
    public PageCompany getPageAllCompanier(int page, int size);

    public Company getCompanyById(Long id);
    public void deleteCompany(Long id);
    public Company modifyCompany(Company company);
}
