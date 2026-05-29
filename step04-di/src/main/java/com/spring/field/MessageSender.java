package com.spring.field;

// 알림 발송 방식을 추상화한 인터페이스.
// EmailSender와 SmsSender가 이 규격을 구현한다.
public interface MessageSender {
    void send(String message);
}
