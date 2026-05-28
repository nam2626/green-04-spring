package annotiation;

import org.springframework.stereotype.Service;

// @Service는 비즈니스 로직을 처리하는 계층의 Bean임을 나타냅니다.
// Controller나 MainApp은 보통 Service를 호출해 실제 작업을 맡깁니다.
@Service
public class ProductService {

	public void processOrder(String productName) {
		// 주문 처리 로직을 콘솔 출력으로 단순화한 예제입니다.
		System.out.println("[ProductService] 주문처리 시작 : "+productName);
	}
}
