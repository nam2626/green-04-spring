package com.spring.field;

import org.springframework.stereotype.Component;

// @Component의 기본 bean 이름은 클래스명의 첫 글자를 소문자로 바꾼 smsSender가 된다.
@Component
public class SmsSender implements MessageSender {
	
	@Override
	public void send(String message) {
		System.out.println("SmsSender: " + message);
	}

}
