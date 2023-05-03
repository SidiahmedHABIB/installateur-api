package com.app.Installateur_API.entity.page;

import com.app.Installateur_API.entity.Admin;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@ToString
@NoArgsConstructor
@Data
public class PageAdmin {
    private List<Admin> admins;
    private int totalPages;
}
