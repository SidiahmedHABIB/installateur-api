package com.app.Installateur_API.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private Date appointmentAt;
    private String status;
    private Date creatAt;
    private Date updateAt;
    @ManyToOne()
    private User user;
    @ManyToOne()
    private Company company;
    @OneToMany(mappedBy = "interventionBox",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Box> intervBoxs = new ArrayList<Box>();
}
