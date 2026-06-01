package com.spring.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 외부 설정 파일(.properties)의 값을 읽어오는 클래스
 * 
 * @Component: 이 클래스를 Spring Bean으로 등록합니다.
 * @PropertySource: 어떤 설정 파일을 읽어올지 경로를 지정합니다.
 */
@Component
@PropertySource("classpath:store.properties")
public class StoreProperties {
	
	/**
	 * @Value("${key}"): 설정 파일에 정의된 key의 값을 필드에 자동으로 주입합니다.
	 */
	@Value("${store.name}")
	private String storeName;

	@Value("${store.openTime}")
	private String openTime;
	
	@Value("${store.maxOrder}")
	private int maxOrder;
	
	public void printInfo() {
		System.out.println("가계명 : " + storeName);
		System.out.println("오픈시간 : " + openTime);
		System.out.println("최대주문 : " + maxOrder);
	}

}





