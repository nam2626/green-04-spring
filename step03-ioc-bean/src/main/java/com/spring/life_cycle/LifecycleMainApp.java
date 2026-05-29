package com.spring.life_cycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifecycleMainApp {

	public static void main(String[] args) {
        System.out.println("▶ ApplicationContext 생성 시작\n");
        // XML 설정 파일을 읽는 순간 singleton bean은 생성되고 init callback까지 실행된다.
        ApplicationContext context = 
        		new ClassPathXmlApplicationContext("applicationContext-lifecyle.xml");
        
		// XML의 init-method / destroy-method 속성으로 생명주기 메서드를 연결한 bean
        DatabaseConnector connector1 = context.getBean(DatabaseConnector.class);
        connector1.query("select * from member");
        DatabaseConnector connector2 = context.getBean(DatabaseConnector.class);
		
        // InitializingBean / DisposableBean 인터페이스로 생명주기 메서드를 구현한 bean
        NetworkService networkService = context.getBean(NetworkService.class);
        
        // @PostConstruct / @PreDestroy 애너테이션으로 생명주기 메서드를 지정한 bean
        CachManager cachManager = context.getBean(CachManager.class);
        
        System.out.println("\n▶ 컨테이너 종료(close()) 시작\n");
        // 소멸할때 호출되는 메서드는 scope가 singleton 일때만 호출됨
        // prototype은 호출이 안됨 - IoC가 생성후에 관리를 안함
        ((ClassPathXmlApplicationContext) context).close(); 
	}

}
