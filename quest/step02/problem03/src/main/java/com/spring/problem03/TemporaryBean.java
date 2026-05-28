package com.spring.problem03;

// TemporaryBean은 prototype 스코프를 확인하기 위한 Bean입니다.
// prototype은 getBean() 호출마다 새 객체를 만들기 때문에 id가 서로 달라집니다.
public class TemporaryBean {

    private final String id;

    public TemporaryBean() {
        // 객체가 새로 만들어졌는지 눈으로 확인하기 위해 임의의 id를 생성합니다.
        this.id = "T-" + (int) (Math.random() * 10000);
    }

    public String getId() {
        return id;
    }
}
