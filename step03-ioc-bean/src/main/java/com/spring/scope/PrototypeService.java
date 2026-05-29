package com.spring.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@Scope("prototype")
public class PrototypeService {

	public PrototypeService() {
		System.out.println("PrototypeService 생성자 호출");
	}

	@PreDestroy
	public void onDestroy() {
		// prototype bean은 컨테이너가 생성 이후의 소멸 callback을 관리하지 않는다.
		System.out.println("PrototypeService onDestroy()");
	}
}







