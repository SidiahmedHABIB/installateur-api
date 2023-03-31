package com.app.Installateur_API.service.classes;

import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.repository.InterventionRepository;
import com.app.Installateur_API.service.interfaces.IInterventionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class InterventionServiceImp implements IInterventionService {
    @Autowired
    private InterventionRepository interventionRepository;
    @Override
    public Intervention creatNewIntervention(Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    @Override
    public List<Intervention> getAllIntervention() {
        return interventionRepository.findAll();
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
}
