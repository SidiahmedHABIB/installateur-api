package com.app.Installateur_API.controller;
import com.app.Installateur_API.entity.Intervention;
import com.app.Installateur_API.entity.PageIntervention;
import com.app.Installateur_API.entity.User;
import com.app.Installateur_API.service.interfaces.IInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/page/{status}&{page}&{size}")
    public ResponseEntity<PageIntervention> getPageInter(@PathVariable String status,@PathVariable int page, @PathVariable int size){
        PageIntervention pageIntervention = iInterventionService.getPageIntervention(status,page, size);
        return ResponseEntity.ok().body(pageIntervention);
    }
    @GetMapping("/pageInterByUser/{uId}&{page}&{size}")
    public ResponseEntity<PageIntervention> getPageAllInterByUser(@PathVariable Long uId,@PathVariable int page, @PathVariable int size){
        PageIntervention pageIntervention = iInterventionService.getPageAllInterByUser(uId,page, size);
        return ResponseEntity.ok().body(pageIntervention);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Intervention> getInterByid(@PathVariable Long id){
        Intervention intervention =  iInterventionService.getInterventionById(id);
        return ResponseEntity.ok().body(intervention);
    }
    @GetMapping("/addAppointment/{uId}&{interId}&{datetime}")
    public ResponseEntity<Intervention> addAppointment(@PathVariable Long uId, @PathVariable Long interId,@PathVariable String datetime){
        Intervention intervention = iInterventionService.addAppointment(uId,interId,datetime);
        return ResponseEntity.ok().body(intervention);
        //return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/plannedIntervention/")
    public ResponseEntity<Intervention> plannedIntervention(@RequestBody Intervention intervention){
        Intervention inter = iInterventionService.modifyIntervention(intervention);
        return ResponseEntity.ok().body(inter);
    }
}
