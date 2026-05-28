package annotiation;

import java.util.List;

import org.springframework.stereotype.Repository;

// @Repository는 데이터 접근 계층의 Bean임을 나타냅니다.
// DB를 직접 연결하지는 않지만, "데이터를 조회하는 역할"을 보여주는 예제입니다.
@Repository
public class ProductRepository {
	public List<String> findAll(){
		// 간단한 실습을 위해 DB 대신 고정된 상품 목록을 반환합니다.
		return List.of("노트북","스마트폰","태블릿");
		
	}
}
