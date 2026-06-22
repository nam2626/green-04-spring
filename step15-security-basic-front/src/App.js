import logo from './logo.svg';
import './App.css';
import { useRef } from 'react';
import axios from 'axios';
const BASE_URL = "http://localhost:8888";

const tokenStore = {
  getAccessToken : ()=> localStorage.getItem("accessToken"),
  setAccessToken : (token)=> localStorage.setItem("accessToken",token),
  getRefreshToken : ()=> localStorage.getItem("refreshToken"),
  setRefreshToken : (token)=> localStorage.setItem("refreshToken",token),
  clearToken : ()=> {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
  }
}

function App() {
  const usernameRef = useRef(null);
  const passwordRef = useRef(null);
  const emailRef = useRef(null);

  const handleRegister = async () => {
    const username = usernameRef.current.value;
    const password = passwordRef.current.value;
    const email = emailRef.current.value;

    console.log(username,password,email);
    // axios 호출해서 회원가입 처리 /auth/signup

    const response = await axios.post(BASE_URL+"/auth/signup",{username,password,email});
    
    console.log(response.data);


  };

  const loginUserName = useRef(null);
  const loginPassword = useRef(null);

  const handleLogin = async () => {
    // 로그인 요청 보내기 
    // /auth/login - post

    // 요청 성공시 accessToken, refreshToken을 localStorage에 저장

    // 화면에 accessToken 출력 로그인 성공 메세지도 출력

    

  }


  return (<div className="container">
    <header>
      <h1>JWT API 테스트 패널</h1>
      <div className='head-content'>Access Token 출력 영역</div>
    </header>
    <div className='result-message-box'>
      <h2>응답결과</h2>
      <div className='message-content'></div>  
    </div>
    <main>
      <section className="card">
        <h2>회원가입</h2>
        <div className='form-group'>
          <label htmlFor="reg-username">아이디</label>
          <input type="text" id="reg-username" ref={usernameRef}/>
        </div>
        <div className='form-group'>
          <label htmlFor="reg-password">패스워드</label>
          <input type="password" id="reg-password" ref={passwordRef}/>
        </div>
        <div className='form-group'>
          <label htmlFor="reg-email">이메일</label>
          <input type="text" id="reg-email" ref={emailRef}/>
        </div>
        <div className='btn-group'>
          <button id="register" onClick={handleRegister}>회원가입</button>
        </div>
      </section>
       <section className="card">
          <h2>로그인</h2>
          <div className='form-group'>
            <label htmlFor="log-username">아이디</label>
            <input type="text" id="log-username" ref={loginUserName}/>
          </div>
          <div className='form-group'>
            <label htmlFor="log-password">패스워드</label>
            <input type="password" id="log-password" ref={loginPassword}/>
          </div>
          <div className='btn-group'>
            <button id="login" onClick={handleLogin}>로그인</button>
          </div>
           
       </section>
    </main>

  </div>
  );
}

export default App;
