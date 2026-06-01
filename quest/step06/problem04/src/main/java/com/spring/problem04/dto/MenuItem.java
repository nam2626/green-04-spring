package com.spring.problem04.dto;

/**
 * [완성된 파일 — 수정 불필요]
 * 메뉴 항목 DTO (Data Transfer Object)
 */
public class MenuItem {

    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
