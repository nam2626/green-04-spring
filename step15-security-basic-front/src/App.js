import logo from './logo.svg';
import './App.css';

function App() {
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
          <input type="text" id="reg-username" />
        </div>
        <div className='form-group'>
          <label htmlFor="reg-password">패스워드</label>
          <input type="password" id="reg-password" />
        </div>
        <div className='form-group'>
          <label htmlFor="reg-email">이메일</label>
          <input type="text" id="reg-email" />
        </div>
        <div className='btn-group'>
          <button id="register">회원가입</button>
        </div>
      </section>
    </main>

  </div>
  );
}

export default App;
