package com.app.Installateur_API.controller;
import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.service.interfaces.IInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inter")
public class InterventionController {
    @Autowired
    private IInterventionService iInterventionService;
    @GetMapping("/all")
    public ResponseEntity<List<Intervention>> getAllIntervention(){
        List<Intervention> intervention = iInterventionService.getAllIntervention();
        return ResponseEntity.ok().body(intervention);
    }
}
