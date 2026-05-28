package com.spring.problem03;

// CounterBean은 singleton 스코프를 확인하기 위한 Bean입니다.
// singleton은 하나의 객체를 공유하기 때문에 count 값도 함께 공유됩니다.
public class CounterBean {

    private int count = 0;

    public void increment() {
        // 호출할 때마다 count를 1씩 증가시킵니다.
        count++;
    }

    public int getCount() {
        return count;
    }
}
