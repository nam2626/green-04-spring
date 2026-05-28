package singleton;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingletonMainApp {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-singleton.xml");
		
		System.out.println("=== Singleton vs Prototype 비교 ===");
		System.out.println("=== Singletone ===");
		CounterBean c1 = context.getBean(CounterBean.class);
		CounterBean c2 = context.getBean(CounterBean.class);
		
		System.out.println("c1 identityHashCode : " + System.identityHashCode(c1) );
		System.out.println("c2 identityHashCode : " + System.identityHashCode(c2) );
		System.out.println("c1 == c2 : " + (c1 == c2));
		
	}

}







