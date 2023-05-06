package com.app.Installateur_API.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseAdmin {
    private  String status;
    private  Admin admin;
}

