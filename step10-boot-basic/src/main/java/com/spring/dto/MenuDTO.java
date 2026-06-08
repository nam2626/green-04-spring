package com.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MenuDTO {
    private Long id;
    private String name;
    private int price;
    private String category;
    private String description;
    private boolean available = true;
}
