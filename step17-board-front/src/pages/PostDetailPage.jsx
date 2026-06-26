import { useState } from "react";
import { useParams } from "react-router-dom"

export default () => {
  const {bno} = useParams();
  const {post ,setPost} = useState({});
  const {commentList, setCommentList} = useState([]);

  return <div className="container">
    <h2>{post.title}</h2>
    <div className="post-meta">
      <span>작성자 : {post.nickname}</span>
      <span>조회수 : {post.bcount}</span>
      <span>최종작성일 : {post.writeUpdateDate}</span>
    </div>
    <hr/>
    <div className="post-content">{post.content}</div>
    <div className="post-footer">
      <div>
        <button>좋아요 👍</button>
        <button>싫어요 👎</button>
      </div>
      <div>
        <button>삭제</button>
        <button>수정</button>
      </div>
    </div>
    <div className="comment-area">
      <div className="comment-form">
        <textarea></textarea><button>댓글<br/>등록</button>
      </div>
      <hr />
      <div className="comment-list">
        
      </div>
    </div>
  </div>
}