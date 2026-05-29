package com.spring.life_cycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// Spring 전용 생명주기 인터페이스를 구현하는 방식의 예제
public class NetworkService implements InitializingBean, DisposableBean{
	
	public NetworkService() {
		System.out.println("[NetworkService] 생성자 - 인스턴스화");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("[NetworkService] destory() - 서버연결 종료");
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("[NetworkService] afterPropertiesSet() - 서버연결");
	}

}

