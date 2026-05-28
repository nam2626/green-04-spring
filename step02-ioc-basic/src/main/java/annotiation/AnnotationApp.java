package annotiation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationApp {

	public static void main(String[] args) {
		// applicationContext-anno.xml에는 component-scan 설정이 들어 있습니다.
		// component-scan은 @Component, @Service, @Repository가 붙은 클래스를 자동으로 Bean 등록합니다.
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext-anno.xml");
		
		System.out.println("=== Annotation 방식 Bean 등록 실습 ===");
		
		// id 속성값으로 읽을 때에는 클래스명에서 첫글자를 소문자로 지정
		// ProductRepository -> productRepository 라는 기본 Bean 이름이 만들어집니다.
		ProductRepository pr = 
				context.getBean("productRepository",ProductRepository.class);
		
		System.out.println("[ProductRepository] 상품 전체 조회 : " + pr.findAll());
		
		// 타입으로만 조회할 수도 있습니다. 같은 타입의 Bean이 하나뿐이면 Spring이 찾아줍니다.
		ProductService service = context.getBean(ProductService.class);
		service.processOrder("스마트폰");
		
		// EmailSender도 @Component 덕분에 XML에 <bean>을 직접 쓰지 않아도 Bean으로 등록됩니다.
		EmailSender email = context.getBean(EmailSender.class);
		email.send("support@spring.com", "주문이 완료되었습니다");
		
		// 예제 실행이 끝나면 컨테이너를 닫아 자원을 정리합니다.
		((ClassPathXmlApplicationContext)context).close();
	}

}






