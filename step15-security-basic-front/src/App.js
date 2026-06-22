import logo from './logo.svg';
import './App.css';
import { useRef } from 'react';
import axios from 'axios';
const BASE_URL = "http://localhost:8888";
function App() {
  const usernameRef = useRef(null);
  const passwordRef = useRef(null);
  const emailRef = useRef(null);

  const handleRegister = () => {
    const username = usernameRef.current.value;
    const password = passwordRef.current.value;
    const email = emailRef.current.value;

    console.log(username,password,email);
    // axios 호출해서 회원가입 처리 /auth/signup

  };

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
    </main>

  </div>
  );
}

export default App;
