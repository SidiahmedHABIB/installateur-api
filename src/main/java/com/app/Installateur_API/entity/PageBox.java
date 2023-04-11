package com.app.Installateur_API.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@ToString
@NoArgsConstructor
@Data
public class PageBox {
    private List<Box> boxes;
    private int totalPages;
}
