package com.spring;

// Calculator는 Spring Bean으로 등록해서 사용할 간단한 사칙연산 예제입니다.
// 기능이 단순해도 Spring 컨테이너에서 객체를 꺼내 쓰는 흐름을 연습할 수 있습니다.
public class Calculator {
	public int add(int a, int b) {
		// 더하기 결과를 반환합니다.
		return a+b;
	}
	public int sub(int a, int b) {
		// 빼기 결과를 반환합니다.
		return a-b;
	}
	public int mul(int a, int b) {
		// 곱하기 결과를 반환합니다.
		return a*b;
	}
	public int div(int a, int b) {
		// 나누기 결과를 반환합니다. 이 예제에서는 b가 0이 아니라고 가정합니다.
		return a/b;
	}
	
}
