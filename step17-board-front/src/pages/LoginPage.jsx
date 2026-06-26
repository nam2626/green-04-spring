import { useRef, useState } from "react";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

export default () => {
  const username = useRef(null);
  const password = useRef(null);
  const [loading, setLoding] = useState(false);
  const {login} = useAuth();
  const navigate = useNavigate();
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = async () => {
    setLoding(true);
    try{
      await login(username.current.value,password.current.value);
      navigate('/');
    }catch(error){
      console.log(error);
      setErrorMessage(error.response?.data?.message || '로그인 실패했습니다.');
    }finally{
      setLoding(false);
    }
  };

  // 로그인 폼
  return <div className="container">
    <h2>로그인 페이지</h2>
    <div className="frm-login">
        <input type="text" placeholder="아이디를 입력하세요" ref={username}/>
        <input type="password" placeholder="암호를 입력하세요" ref={password}/>
        {errorMessage && <div className="error-message-box">{errorMessage}</div>}
        {loading ? <p>현재 로그인 중입니다.</p> : <button onClick={handleLogin}>로그인</button>}
        <button onClick={() => navigate('/auth/signup')}>회원가입</button>

    </div>
  </div>
}