package com.spring.problem01;

import java.util.List;
import java.util.Map;

// MenuBean은 식당의 메뉴 정보를 담는 단순 Java 객체입니다.
// XML 설정에서 문자열, List, Map 값을 주입받는 연습에 사용합니다.
public class MenuBean {

    // category는 "한식"처럼 메뉴의 큰 분류를 나타냅니다.
    private String              category;
    // items는 여러 메뉴 이름을 순서대로 담는 List 주입 예제입니다.
    private List<String>        items;
    // prices는 메뉴 이름과 가격을 key-value로 담는 Map 주입 예제입니다.
    private Map<String, String> prices;

    public void printInfo() {
        // XML에서 주입된 값들이 객체 안에 잘 들어왔는지 콘솔로 확인합니다.
        System.out.println("[MenuBean] 카테고리: " + category);
        System.out.println("  메뉴 목록: " + items);
        System.out.println("  가격표: " + prices);
    }

    // setter는 Spring이 <property> 값을 넣을 때 호출하는 통로입니다.
    public String getCategory()                    { return category; }
    public void   setCategory(String category)     { this.category = category; }

    public List<String> getItems()                 { return items; }
    public void         setItems(List<String> items){ this.items = items; }

    public Map<String, String> getPrices()                    { return prices; }
    public void                setPrices(Map<String, String> prices) { this.prices = prices; }
}
