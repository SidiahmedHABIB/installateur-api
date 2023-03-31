package com.app.Installateur_API.entity;

import com.app.Installateur_API.enmus.InterStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
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
    //@Enumerated(EnumType.STRING)
    private InterStatus status;
    private Date creatAt;
    private Date updateAt;
    @ManyToOne()
    private User user;
    @ManyToOne()
    private Company company;
}
