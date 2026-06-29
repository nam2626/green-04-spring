import { useContext, useState } from "react";
import QuillEditor from "../components/QuillEditor"


export default () => {
  // 수정인지? 작성인지?
  // 1. 제목, quill Editor 글내용 입력 받는 폼
  // 2. 글쓰기 버튼 클릭시 게시글 전송
  const defaultValue = "<h2>샘플 제목</h2><p>Quill <em>에디터</em> 테스트</p>";
  const [postDetail, setPostDetail] = useState('');

  const onChangePostDetail = (newPostDetail) => {
    setPostDetail(newPostDetail);
  }

  return <div className="container">
    <h2>글쓰기</h2>
    <div className="post-title">
      <label>제목</label>
      <input type="text" placeholder="제목을 입력하세요"/>
    </div>
    <div className="post-detail">
      <label>내용</label>
      <div id="editor">
        <QuillEditor onChange={onChangePostDetail} defaultVale={defaultValue}/>
      </div>
    </div>
    <div className="error-box"></div>
    <div className="post-actions">
      <button>글쓰기</button><button>취소</button>
    </div>
  </div>
}
