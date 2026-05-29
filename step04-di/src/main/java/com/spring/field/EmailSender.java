package com.spring.field;

import org.springframework.stereotype.Component;

// bean 이름을 직접 emailSender로 지정한 구현체.
@Component("emailSender")
public class EmailSender implements MessageSender {
	
	@Override
	public void send(String message) {
		System.out.println("EmailSender: " + message);
	}

}
