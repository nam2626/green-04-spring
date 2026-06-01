package com.spring.qualifier;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PaymentMain {

	public static void main(String[] args) {
		System.out.println("=== @Qualifier / @Primary ===");
		AnnotationConfigApplicationContext context 
				= new AnnotationConfigApplicationContext(AppConfig.class);
		
		PaymemtService paymemtService = context.getBean(PaymemtService.class);
		
		paymemtService.processPayment(30000);
		
		context.close();
	}

}
