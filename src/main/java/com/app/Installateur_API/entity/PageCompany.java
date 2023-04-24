package com.app.Installateur_API.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class PageCompany {
    private List<Company> companies;
    private int totalPages;
}
