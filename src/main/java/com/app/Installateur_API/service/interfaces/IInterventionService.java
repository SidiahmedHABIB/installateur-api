package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.entity.PageIntervention;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

public interface IInterventionService {
    public Intervention creatNewIntervention(Intervention intervention);
    public List<Intervention> getAllIntervention();
    public PageIntervention getPageIntervention(String status,int page, int size);
    public Intervention getInterventionById(Long id);
    public void deleteIntervention(Long id);
    public Intervention modifyIntervention(Intervention intervention);
    public Intervention addAppointment(Long uId, Long interId,String datetime);
}
