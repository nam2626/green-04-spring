package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

// Refresh Token을 DB에 저장하고 조회할 저장소의 뼈대이다.
// 현재는 엔티티 타입과 기본 키 타입이 지정되지 않은 미완성 상태이며 다음 작업에서 구체화한다.
public interface RefreshTokenRepository extends JpaRepository{

}
