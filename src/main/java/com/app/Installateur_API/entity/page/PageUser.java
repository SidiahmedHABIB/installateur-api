package com.app.Installateur_API.entity.page;

import com.app.Installateur_API.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class PageUser {
    private List<User> users;
    private int totalPages;
}
