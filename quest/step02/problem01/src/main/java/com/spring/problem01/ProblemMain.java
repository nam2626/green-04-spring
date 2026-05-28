package com.spring.problem01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProblemMain {

    public static void main(String[] args) {
        // applicationContext.xml 완성 후 실행하면 정상 출력된다.
        // XML 파일을 읽어 Spring 컨테이너를 만들고, 등록된 Bean들을 사용할 준비를 합니다.
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // restaurantBean을 꺼내면 XML ref 설정 덕분에 menuBean도 이미 연결되어 있습니다.
        RestaurantBean restaurant = context.getBean("restaurantBean", RestaurantBean.class);
        restaurant.printInfo();

        // 예제 실행 후 컨테이너를 닫아 Spring이 관리하던 자원을 정리합니다.
        ((ClassPathXmlApplicationContext) context).close();
    }
}
