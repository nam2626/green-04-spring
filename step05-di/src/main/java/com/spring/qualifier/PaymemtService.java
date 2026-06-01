package com.spring.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymemtService {
	private final PaymentGateway paymentGateway;

	@Autowired
	public PaymemtService(@Qualifier("kakaoPaymentGateway") PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	
	public void processPayment(int amount) {
		System.out.println(paymentGateway.pay(amount));
	}
	
	
}





