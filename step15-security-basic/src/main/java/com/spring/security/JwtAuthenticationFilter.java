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

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserDetailsService userDetailsService;
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
        //1. Authorization 헤더에서 Bearer 토큰 추출
        String token = extractToken(request);
        // 2. 토큰이 존재하고 유효하면 SecurityContext에 인증 정보를 저장
        if(token != null && jwtTokenProvider.validateToken(token)){
          // 3. 토큰에서 username을 추출 -> DB 조회
          String username = jwtTokenProvider.getUsername(token);
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          // 4. 인증객체 생성
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

          // 요청 정보(IP, 세션 ID 등) 추가
          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

           // ⑤ SecurityContext에 저장 → 컨트롤러에서 @AuthenticationPrincipal로 꺼낼 수 있음
           SecurityContextHolder.getContext().setAuthentication(authenticationToken);

           // 인증 설정 끝남
        }
        // 6. 다음 필터로 전달(반드시 해야함)
        filterChain.doFilter(request, response);
  }

  private String extractToken(HttpServletRequest request) {
    String header = request.getHeader("Authorization");
    if(header != null && header.startsWith("Bearer ")){
      return header.substring(7);
    }
    return null;
  }

}
