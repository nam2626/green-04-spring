package com.spring.problem02;

import org.springframework.stereotype.Service;

// TODO [문제 2-2] 이 클래스를 서비스 레이어 Bean으로 등록하는 어노테이션을 추가하세요.
//  힌트: @Service
@Service
public class PaymentService {

    public void processPayment(int amount) {
        System.out.println("[PaymentService] 결제 처리 시작: " + amount + "원");
    }
}
