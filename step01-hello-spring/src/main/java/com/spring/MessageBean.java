package com.spring;

// MessageBean은 Spring 컨테이너에 등록해서 꺼내 쓸 가장 기본적인 Bean 예제입니다.
// Bean은 Spring이 생성하고 관리해 주는 객체라고 이해하면 됩니다.
public class MessageBean {
	// message 값은 생성자나 setter를 통해 외부에서 주입할 수 있습니다.
	private String message;

	public MessageBean() {
		// 아무 값도 주입하지 않았을 때 사용할 기본 메시지입니다.
		this.message = "Hello Spring!";
	}

	public MessageBean(String message) {
		// XML의 <constructor-arg>를 사용하면 이 생성자로 message 값을 넣을 수 있습니다.
		this.message = message;
	}
	
	public void printMessage() {
		// Bean이 가진 값을 콘솔에 출력해 주입 결과를 확인합니다.
		System.out.println("[MessageBean] : " + this.message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		// XML의 <property>를 사용하면 Spring이 이 setter를 호출해 값을 넣습니다.
		this.message = message;
	}
	
	
	
	
}
