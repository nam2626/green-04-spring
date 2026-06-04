package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
//				.allowedOrigins("*") // 모든 출처 허용 (실제 운영에서는 특정 출처로 제한하는 것이 좋음)
//				.allowedMethods("*") // 모든 HTTP 메서드 허용
//				.allowedHeaders("*") // 모든 헤더 허용
//				.allowCredentials(false).maxAge(3600); // 1시간 동안 preflight 요청 캐시
//	}
//	
	
}
