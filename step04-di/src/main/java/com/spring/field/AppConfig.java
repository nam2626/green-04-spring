package com.spring.field;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// @Component, @Service가 붙은 클래스를 이 패키지 아래에서 찾아 bean으로 등록한다.
@ComponentScan(basePackages = "com.spring.field")
public class AppConfig {
	
}
