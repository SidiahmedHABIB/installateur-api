package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.entity.page.PageIntervention;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.util.List;

public interface IInterventionService {
    public Intervention creatNewIntervention(Intervention intervention,Long id);
    public List<Intervention> getAllIntervention();
    public PageIntervention getPageIntervention(String status,int page, int size);
    public PageIntervention getPageInterPlannedByUser(Long uId,String status,int page, int size);
    public PageIntervention getPageAllInterByUser(Long uId,int page, int size);
    public List<Intervention> getAllInterByUser(Long uId);
    public PageIntervention getPageAllInterByCompany(Long companyId,int page, int size);

    public Intervention getInterventionById(Long id);
    public void deleteIntervention(Long id);
    public Intervention modifyIntervention(Intervention intervention);
    public Intervention upadateIntervention(Intervention intervention,Long id);
    public Intervention addAppointment(Long uId, Long interId,String appointement) throws ParseException;
}
