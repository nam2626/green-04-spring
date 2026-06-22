import logo from './logo.svg';
import './App.css';
import { useRef } from 'react';

function App() {
  const usernameRef = useRef(null);
  const passwordRef = useRef(null);
  const emailRef = useRef(null);

  const handleRegister = () => {
    const username = usernameRef.current.value;
    const password = passwordRef.current.value;
    const email = emailRef.current.value;

    alert(`${username} ${password} ${email} 회원가입 버튼 클릭됨`);
  };

  return (<div className="container">
    <header>
      <h1>JWT API 테스트 패널</h1>
      <div className='head-content'>Access Token 출력 영역</div>
    </header>

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
