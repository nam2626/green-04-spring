package com.spring.setter;

public class MenuService {
    private MenuRepository menuRepository;
    private String storeName;

    // setter 주입: bean 생성 후 Spring이 property 값을 setter로 넣어준다.
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    // XML의 <property name="storeName" value="..."/> 값이 이 메서드로 주입된다.
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void printMenus() {
        System.out.println("[MenuService] 매장명: " + storeName);
        System.out.println("[MenuService] 메뉴 목록: " + menuRepository.findAll());
    }
}
