package com.spring.problem01;

// RestaurantBean은 다른 Bean(MenuBean)을 참조로 주입받는 예제입니다.
// 직접 new MenuBean()을 하지 않고, XML의 ref 설정으로 연결받습니다.
public class RestaurantBean {

    // name은 단순 문자열 값 주입 예제입니다.
    private String   name;
    // menu는 Spring 컨테이너가 만들어 둔 menuBean 객체를 주입받습니다.
    private MenuBean menu;

    public void printInfo() {
        // restaurantBean 안에 menuBean이 제대로 연결됐는지 함께 출력합니다.
        System.out.println("[RestaurantBean] 식당명: " + name);
        menu.printInfo();
    }

    // 아래 setter들은 Spring이 <property> 값을 주입할 때 사용합니다.
    public String getName()           { return name; }
    public void   setName(String n)   { this.name = n; }

    public MenuBean getMenu()         { return menu; }
    public void     setMenu(MenuBean m){ this.menu = m; }
}
