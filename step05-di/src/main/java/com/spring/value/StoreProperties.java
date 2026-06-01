package com.spring.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:store.properties")
public class StoreProperties {
	
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





