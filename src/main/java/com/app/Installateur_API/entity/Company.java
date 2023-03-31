package com.app.Installateur_API.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String phone;
    private String email;
    private Date creatAt;
    private Date updateAt;
    @OneToOne
    private ImageData imageCompany;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> companyInterventions = new ArrayList<Intervention>();
    @OneToMany(mappedBy = "companyBox",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Box> companyBoxs = new ArrayList<Box>();


}
