package com.spring.setter;

import java.util.List;

// 메뉴 데이터를 조회하는 저장소 역할의 객체.
public class MenuRepository {
    public List<String> findAll() {
        return List.of("아메리카노", "카페라떼", "바닐라라떼");
    }
}
