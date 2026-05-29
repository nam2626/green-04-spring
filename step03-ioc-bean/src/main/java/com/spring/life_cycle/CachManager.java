package com.spring.life_cycle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
// singleton은 Spring의 기본 scope이며, 컨테이너 종료 시 @PreDestroy가 호출된다.
// prototype으로 바꾸면 생성 이후 컨테이너가 소멸 callback을 관리하지 않는다.
@Scope("singleton")
public class CachManager {

	public CachManager() {
		System.out.println("[CachManager] 생성자 - 인스턴스화");
	}
	
	@PostConstruct
	public void initCache() {
		System.out.println("[CachManager] PostConstruct");
	}

	@PreDestroy
	public void clearCache() {
		System.out.println("[CachManager] PreDestroy");
	}
		
}








