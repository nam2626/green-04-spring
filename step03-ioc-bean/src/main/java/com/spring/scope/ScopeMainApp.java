package com.spring.scope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeMainApp {

	public static void main(String[] args) {
		// try-with-resources를 사용하면 main 종료 시 ctx.close()가 자동 호출된다.
		// singleton bean의 @PreDestroy는 close() 시점에 실행된다.
		try(AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig.class)){
			
			// prototype은 getBean()을 호출할 때마다 새로운 인스턴스를 만든다.
			PrototypeService prototypeService1 
				= ctx.getBean("prototypeService",PrototypeService.class);
			PrototypeService prototypeService2 
			= ctx.getBean("prototypeService",PrototypeService.class);
			
			System.out.println("prototypeService1 == prototypeService2 : "
					+ (prototypeService1 == prototypeService2));
			
			// singleton은 컨테이너 안에 하나만 만들어두고 같은 인스턴스를 반환한다.
			SingletonService singletonService1 = ctx.getBean(SingletonService.class);
			SingletonService singletonService2 = ctx.getBean(SingletonService.class);
			
			System.out.println("singletonService1 == singletonService2 : "
					+ (singletonService1 == singletonService2));
		}
	}

}
