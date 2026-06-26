import { Link } from "react-router-dom"

export default () => {
  const isAuthenticate = false;
  return <nav className="navbar">
    <Link to="/" className="navbar-brand">📄 Spring Board</Link>
    <div className="navbar-menu">
      {
        isAuthenticate ?
          <>
            <span className="navbar-user">사용자이름</span>
            <Link to="/posts/create" className="nav-link">글쓰기</Link>
            <button type="button" className="nav-btn-secondary">로그아웃</button>
          </>
          :
          <>
            <Link to="/login" className="nav-link">로그인</Link>
            <Link to="/signup" className="nav-btn">회원가입</Link>
          </>
      }
    </div>

  </nav>
}