import { createContext, useEffect, useState } from "react";
import { authApi } from "../api/authApi";
/**
 * 전역 인증 상태 관리
 * 
 * Context API + useState로 구현
 */

const AuthContext = createContext(null);

export function AuthProvider({children}){
  const [user, setUser] = useState(null);
  const [loading, setLoding ] = useState(true);

  // 앱 시작시 : localStorage에 accessToken이 있으면 사용자 정보 요청해서 받음
  useEffect(() => {
    const token = localStorage.getItem('accessToken');

    if(token){
      authApi.me()
      .then(response => {
        setUser(response.data);
      })
      .catch(error => localStorage.clear())
      .finally(() => setLoding(false));
    }else{
      setLoding(false);
    }
  },[]);

  // 로그인

  // 로그아웃
}