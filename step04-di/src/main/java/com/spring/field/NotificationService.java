package com.spring.field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
	
	// 같은 MessageSender 구현체가 2개 있으므로 @Qualifier로 사용할 bean을 지정한다.
	@Autowired
	@Qualifier("smsSender")
	private MessageSender messageSender;
	
	public void notifyOrder(String message) {
		messageSender.send(message);
		System.out.println("[NotificationService] 알림처리 완료");
	};
}
