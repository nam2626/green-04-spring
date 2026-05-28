package com.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		// 1. Spring 컨테이너 생성
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("=== Spring 컨테이너 생성 완료 ===");
		// 2. MessageBean 꺼내기
//		MessageBean messageBean = (MessageBean) context.getBean("messageBean");
		MessageBean messageBean = context.getBean("messageBean", MessageBean.class);
		messageBean.printMessage();
		
	}

}





