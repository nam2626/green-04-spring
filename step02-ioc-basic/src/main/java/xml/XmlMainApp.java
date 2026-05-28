package xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlMainApp {

	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-xml.xml");
		
		System.out.println("=== XML 방식 Bean 등록 실습 ===");
		BookBean book1 = context.getBean("book1",BookBean.class);
		System.out.println("[Book1 - setter 주입]");
		System.out.println(book1);
		
		BookBean book2 = context.getBean("book2",BookBean.class);
		System.out.println("[Book2 - 생성자 주입]");
		System.out.println(book2);
		
		LibraryBean libraryBean = context.getBean("libraryBean",LibraryBean.class);
		libraryBean.printInfo();
		
		((ClassPathXmlApplicationContext) context).close();
		
	}

}









