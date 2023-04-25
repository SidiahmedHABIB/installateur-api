package com.app.Installateur_API.controller;
import com.app.Installateur_API.entity.Box;
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
    @GetMapping("/pageStatusByUser/{id}&{status}&{page}&{size}")
    public ResponseEntity<PageIntervention> getPageInterByUser(@PathVariable Long id,@PathVariable String status,@PathVariable int page, @PathVariable int size){
        PageIntervention pageIntervention = iInterventionService.getPageInterPlannedByUser(id,status,page, size);
        return ResponseEntity.ok().body(pageIntervention);
    }
    @GetMapping("/pageInterByUser/{uId}&{page}&{size}")
    public ResponseEntity<PageIntervention> getPageAllInterByUser(@PathVariable Long uId,@PathVariable int page, @PathVariable int size){
        PageIntervention pageIntervention = iInterventionService.getPageAllInterByUser(uId,page, size);
        return ResponseEntity.ok().body(pageIntervention);
    }
    @GetMapping("/pageInterByCompany/{companyId}&{page}&{size}")
    public ResponseEntity<PageIntervention> getPageAllInterByCompany(@PathVariable Long companyId,@PathVariable int page, @PathVariable int size){
        PageIntervention pageIntervention = iInterventionService.getPageAllInterByCompany(companyId,page, size);
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
    @PutMapping("/add/{id}")
    public ResponseEntity<Intervention> creatNewIntervention(@PathVariable Long id, @RequestBody  Intervention inter){
        Intervention interSaved = iInterventionService.creatNewIntervention(inter,id);
        return ResponseEntity.ok().body(interSaved);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Intervention> updateIntervention(@PathVariable Long id,@RequestBody  Intervention inter){
        Intervention interUpdated = iInterventionService.upadateIntervention(inter,id);
        return ResponseEntity.ok().body(interUpdated);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteInterventionById(@PathVariable Long id){
        iInterventionService.deleteIntervention(id);
        return ResponseEntity.ok().body(true);
    }
}
