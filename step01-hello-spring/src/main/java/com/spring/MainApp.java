package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// 1. Spring 컨테이너 생성
		// applicationContext.xml을 읽어서 XML에 등록된 Bean 객체들을 생성합니다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("=== Spring 컨테이너 생성 완료 ===");
		// 2. MessageBean 꺼내기
//		MessageBean messageBean = (MessageBean) context.getBean("messageBean");
		// getBean("id", 타입.class)은 지정한 id의 Bean을 원하는 타입으로 꺼내는 방법입니다.
		MessageBean messageBean = context.getBean("messageBean", MessageBean.class);
		messageBean.printMessage();
		
		// 3. customMessageBean 꺼내기
		// customMessageBean은 XML에서 생성자 인자를 넣어 만든 MessageBean입니다.
		MessageBean customMessageBean = 
				context.getBean("customMessageBean", MessageBean.class);
		customMessageBean.printMessage();
		
		// 4. Calculator 꺼내기
//		Calculator calculator = context.getBean(Calculator.class);
		// calculator Bean을 꺼내 Spring이 만든 객체로 사칙연산 메서드를 호출합니다.
		Calculator calculator = context.getBean("calculator",Calculator.class);
		
		System.out.println("[Calculator] : " + calculator.add(10, 5));
		System.out.println("[Calculator] : " + calculator.sub(10, 5));
		System.out.println("[Calculator] : " + calculator.mul(10, 5));
		System.out.println("[Calculator] : " + calculator.div(10, 5));
		
		// 5. 컨테이너 종료 - Bean을 소멸하는 콜백 호출
		// 예제 실행이 끝났으므로 컨테이너를 닫아 자원을 정리합니다.
		((ClassPathXmlApplicationContext)context).close();
		System.out.println("==== Spring 컨테이너 종료 ====");
		
	}

}







