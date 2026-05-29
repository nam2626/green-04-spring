package com.spring.setter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterMain {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext-setter.xml")) {
			// menuService bean은 XML의 property 설정으로 MenuRepository와 storeName을 주입받는다.
			MenuService menuService = context.getBean(MenuService.class);
			
			System.out.println("--- setter 주입 실습 ---");
			menuService.printMenus();
		}
	}

}
