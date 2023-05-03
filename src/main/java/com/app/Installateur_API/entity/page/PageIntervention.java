package com.app.Installateur_API.entity.page;

import com.app.Installateur_API.entity.Intervention;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@ToString
@NoArgsConstructor
@Data
public class PageIntervention {
    private List<Intervention> interventions;
    private int totalPages;
}
