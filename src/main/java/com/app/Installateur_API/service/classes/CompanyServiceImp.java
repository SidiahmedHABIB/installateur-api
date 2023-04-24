package com.app.Installateur_API.service.classes;



import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.PageCompany;
import com.app.Installateur_API.entity.PageUser;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.repository.CompanyRepository;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImp implements ICompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public Company creatNewCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public PageCompany getPageAllCompanier(int page, int size) {
        PageCompany p= new PageCompany();
        Page<Company> userPage = companyRepository.findAll(PageRequest.of(page, size));
        p.setCompanies(userPage.getContent());
        p.setTotalPages(userPage.getTotalPages());
        return p;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company modifyCompany(Company company) {
        company.setUpdateAt(new Date());
        return companyRepository.save(company);
    }
}
