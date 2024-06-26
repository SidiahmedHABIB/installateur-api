package com.app.Installateur_API.entity;

import com.app.Installateur_API.enmus.BoxStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String name;
    private String entity;
    private String matricul;
    private String boxValue;
    private String nserie;
    private Boolean isSend;
    private Date creatAt;
    private Date updateAt;
    @ManyToOne()
    private Intervention interventionBox;

    @OneToMany(mappedBy = "box",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ImageData> boxImages = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Report reportBox;

}
