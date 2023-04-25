package com.app.Installateur_API.service.classes;

import com.app.Installateur_API.entity.Company;
import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.entity.PageIntervention;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.repository.InterventionRepository;
import com.app.Installateur_API.service.interfaces.ICompanyService;
import com.app.Installateur_API.service.interfaces.IInterventionService;
import com.app.Installateur_API.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class InterventionServiceImp implements IInterventionService {
    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private ICompanyService iCompanyService;
    @Override
    public Intervention creatNewIntervention(Intervention intervention,Long id) {
        Company company = iCompanyService.getCompanyById(id);
        intervention.setCompany(company);
        intervention.setStatus("TOPLAN");
        intervention.setCreatAt(new Date());
        intervention.setUpdateAt(new Date());
        return interventionRepository.save(intervention);
    }

    @Override
    public List<Intervention> getAllIntervention() {
        return interventionRepository.findAll();
    }

    @Override
    public PageIntervention getPageIntervention(String status,int page, int size) {
        PageIntervention p= new PageIntervention();
        Page<Intervention> interventionPage = interventionRepository.findByStatus(status,PageRequest.of(page, size));
        p.setInterventions(interventionPage.getContent());
        p.setTotalPages(interventionPage.getTotalPages());
        return p;
    }

    @Override
    public PageIntervention getPageInterPlannedByUser(Long uId, String status, int page, int size) {
        User user = iUserService.getUserById(uId);
        PageIntervention p= new PageIntervention();
        Page<Intervention> interventionPage = interventionRepository.findByUserAndStatus(user,status,PageRequest.of(page, size));
        p.setInterventions(interventionPage.getContent());
        p.setTotalPages(interventionPage.getTotalPages());
        return p;
    }

    @Override
    public PageIntervention getPageAllInterByUser(Long uId, int page, int size) {
        User user = iUserService.getUserById(uId);
        PageIntervention p= new PageIntervention();
        Page<Intervention> interventionPage = interventionRepository.findByUser(user,PageRequest.of(page, size));
        p.setInterventions(interventionPage.getContent());
        p.setTotalPages(interventionPage.getTotalPages());
        return p;
    }

    @Override
    public PageIntervention getPageAllInterByCompany(Long companyId, int page, int size) {
        Company company = iCompanyService.getCompanyById(companyId);
        PageIntervention p= new PageIntervention();
        Page<Intervention> interventionPage = interventionRepository.findByCompany(company,PageRequest.of(page, size));
        p.setInterventions(interventionPage.getContent());
        p.setTotalPages(interventionPage.getTotalPages());
        return p;
    }


    @Override
    public Intervention getInterventionById(Long id) {
        return interventionRepository.findById(id).get();
    }

    @Override
    public void deleteIntervention(Long id) {
        interventionRepository.deleteById(id);
    }

    @Override
    public Intervention upadateIntervention(Intervention intervention, Long id) {
        Company company = iCompanyService.getCompanyById(id);
        intervention.setCompany(company);
        intervention.setUpdateAt(new Date());
        return interventionRepository.save(intervention);
    }

    @Override
    public Intervention modifyIntervention(Intervention intervention) {
        intervention.setUpdateAt(new Date());
        return interventionRepository.save(intervention);
    }

    @Override
    public Intervention addAppointment(Long uId, Long interId, String datetime) {
        User user = iUserService.getUserById(uId);
        Intervention intervention = getInterventionById(interId);
        intervention.setUser(user);
        intervention.setStatus("ONHOLD");
        intervention.setAppointmentAt(datetime);
        intervention.setUpdateAt(new Date());
        return interventionRepository.save(intervention);
    }
}
