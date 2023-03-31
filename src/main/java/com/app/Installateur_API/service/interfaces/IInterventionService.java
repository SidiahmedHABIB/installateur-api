package com.app.Installateur_API.service.interfaces;

import com.app.Installateur_API.entity.Intervention;

import java.util.List;

public interface IInterventionService {
    public Intervention creatNewIntervention(Intervention intervention);
    public List<Intervention> getAllIntervention();
    public Intervention getInterventionById(Long id);
    public void deleteIntervention(Long id);
    public Intervention modifyIntervention(Intervention intervention);
}
