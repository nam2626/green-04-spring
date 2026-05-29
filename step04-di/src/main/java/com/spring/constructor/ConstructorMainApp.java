package com.spring.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorMainApp {

	public static void main(String[] args) {
		// XML 설정 파일을 읽으면 Spring 컨테이너가 bean을 생성하고 의존성을 주입한다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-constructor.xml");
		
		// KioskOrderService는 생성자를 통해 OrderRepository와 DiscountPolicy를 주입받은 상태다.
		KioskOrderService kioskOrderService = context.getBean(KioskOrderService.class);
		
		System.out.println("---- 생성자 주입 실습 ----");
		kioskOrderService.order("아메리카노", 5, 25000);
		
		((ClassPathXmlApplicationContext) context).close();
	}

}
