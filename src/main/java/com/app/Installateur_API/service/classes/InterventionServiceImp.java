package com.app.Installateur_API.service.classes;

import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.entity.PageIntervention;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.repository.InterventionRepository;
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
    @Override
    public Intervention creatNewIntervention(Intervention intervention) {
        Intervention newIntervention = new Intervention();
        newIntervention.setComment(intervention.getComment());
        newIntervention.setStatus("TOPLAN");
        newIntervention.setAppointmentAt(null);
        newIntervention.setUser(null);
        newIntervention.setCompany(intervention.getCompany());
        newIntervention.setCreatAt(new Date());
        newIntervention.setUpdateAt(new Date());
        return interventionRepository.save(newIntervention);
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
    public Intervention getInterventionById(Long id) {
        return interventionRepository.findById(id).get();
    }

    @Override
    public void deleteIntervention(Long id) {
        interventionRepository.deleteById(id);
    }

    @Override
    public Intervention modifyIntervention(Intervention intervention) {
        return null;
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
