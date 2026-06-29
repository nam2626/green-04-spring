

export default () => {
  // 수정인지? 작성인지?
  // 1. 제목, quill Editor 글내용 입력 받는 폼
  // 2. 글쓰기 버튼 클릭시 게시글 전송

  return <div className="container">
    <h2>글쓰기</h2>
    <div className="post-title">
      <label>제목</label>
      <input type="text" placeholder="제목을 입력하세요"/>
    </div>
    <div className="post-detail">
      <label>내용</label>
      <div id="editor"></div>
      <Quill/>
    </div>
    <div className="error-box"></div>
    <div className="post-actions">
      <button>글쓰기</button><button>취소</button>
    </div>
  </div>
}
