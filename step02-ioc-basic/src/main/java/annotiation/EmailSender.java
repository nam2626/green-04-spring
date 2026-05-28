package annotiation;

import org.springframework.stereotype.Component;

// @Component는 "이 클래스도 Spring Bean으로 등록해 주세요"라는 표시입니다.
// 특별한 계층 의미가 없을 때 사용하는 가장 일반적인 컴포넌트 어노테이션입니다.
@Component
public class EmailSender {
	public void send(String to, String message) {
		// 실제 메일 발송 대신 콘솔 출력으로 동작을 확인하는 예제입니다.
		System.out.println("[EmailSender] "+ to + " 에게 전송 : " + message);
	}
}
