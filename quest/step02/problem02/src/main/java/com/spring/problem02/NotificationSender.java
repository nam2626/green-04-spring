package com.spring.problem02;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

// TODO [문제 2-3] 이 클래스를 일반 컴포넌트 Bean으로 등록하는 어노테이션을 추가하세요.
//  힌트: @Component
@Component
//@Scope("prototype")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NotificationSender {

    public void send(String message) {
        System.out.println("[NotificationSender] 알림 전송: " + message);
    }
}
