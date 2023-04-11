package com.app.Installateur_API.service.classes;



import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.repository.CompanyRepository;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    @Override
    public Company modifyCompany(Company company) {
        Company updateC = new Company();
        updateC.setId(company.getId());
        updateC.setName(company.getName());
        updateC.setEmail(company.getEmail());
        updateC.setLocation(company.getLocation());
        updateC.setPhone(company.getPhone());
        updateC.setImageCompany(company.getImageCompany());
        updateC.setCreatAt(company.getCreatAt());
        updateC.setUpdateAt(new Date());
        return companyRepository.save(company);
    }
}
