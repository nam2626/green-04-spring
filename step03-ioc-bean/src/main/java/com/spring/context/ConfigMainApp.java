package com.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigMainApp {

	public static void main(String[] args) {
		System.out.println("=== [Java Config] AnnotationConfigApplicationContext ===\n");
		
		try(AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig.class)){
			
			ReportService reportService1 = ctx.getBean(ReportService.class);
			ReportService reportService2 = ctx.getBean("reportService",ReportService.class);
			
			System.out.println(reportService1 == reportService2);
			
		}
	}

}
