package com.spring.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymemtService {
	private final PaymentGateway paymentGateway;

	// 구버전에서는 이렇게 어노테이션 지정해야 역주입 가능 
//	@Autowired
//	public PaymemtService(@Qualifier("kakaoPaymentGateway") PaymentGateway paymentGateway) {
//		this.paymentGateway = paymentGateway;
//	}

	public PaymemtService(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	
	public void processPayment(int amount) {
		System.out.println(paymentGateway.pay(amount));
	}
	
	
}





