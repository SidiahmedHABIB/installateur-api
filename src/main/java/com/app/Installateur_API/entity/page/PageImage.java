package com.app.Installateur_API.entity.page;

import com.app.Installateur_API.entity.ImageData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@NoArgsConstructor
@Data
public class PageImage {
    private List<ImageData> images;
    private int totalPages;
}
