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
		
		c1.increment();
		c1.increment();
		c1.increment();
		c2.increment();
		System.out.println(c1.getCount() + " " + c2.getCount());
		
		System.out.println("=== Prototype ===");
		TemporaryBean b1 = context.getBean(TemporaryBean.class);
		TemporaryBean b2 = context.getBean(TemporaryBean.class);
		
		System.out.println("b1 identityHashCode : " + System.identityHashCode(b1) );
		System.out.println("b2 identityHashCode : " + System.identityHashCode(b2) );
		System.out.println("b1 == b2 : " + (b1 == b2));
		
		((ClassPathXmlApplicationContext) context).close();
		
	}

}











