package com.app.Installateur_API.entity.page;

import com.app.Installateur_API.entity.Notice;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class PageNotice {
    private List<Notice> notices;
    private int totalPages;
}
