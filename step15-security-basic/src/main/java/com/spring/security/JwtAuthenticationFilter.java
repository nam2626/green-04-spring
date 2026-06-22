package com.spring.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// 요청이 컨트롤러에 도착하기 전에 JWT를 검사하는 Spring Bean이다.
@Component
// final 필드를 매개변수로 받는 생성자를 Lombok이 자동으로 만들어 준다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        // 1. HTTP Authorization 헤더에서 실제 JWT 문자열을 꺼낸다.
        String token = extractToken(request);
        // 2. 토큰이 존재하고 서명·만료 검사를 통과한 요청만 인증 처리한다.
        if(token != null && jwtTokenProvider.validateToken(token)){
          // 3. 토큰에 적힌 아이디로 DB의 최신 회원 정보와 권한을 조회한다.
          String username = jwtTokenProvider.getUsername(token);
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          // 4. 비밀번호 재검사 없이, JWT 검증을 마친 회원의 인증 객체를 만든다.
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

          // 인증 객체에 현재 요청의 IP 주소, 세션 ID 같은 부가 정보를 담는다.
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

           // 5. 현재 요청의 인증 보관소에 저장하면 이후 컨트롤러에서 인증 회원을 사용할 수 있다.
           // @AuthenticationPrincipal로 꺼낼수 있음
           SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // 6. 인증 성공 여부와 관계없이 다음 필터로 요청을 넘겨야 전체 필터 체인이 계속 동작한다.
        filterChain.doFilter(request, response);
  }

  // "Bearer " 접두어를 제외한 토큰 부분만 반환한다. 조건에 맞지 않으면 토큰이 없다는 뜻으로 null을 반환한다.
  private String extractToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if(header != null && header.startsWith("Bearer ")){
      return header.substring(7);
    }
    return null;
  }

}
