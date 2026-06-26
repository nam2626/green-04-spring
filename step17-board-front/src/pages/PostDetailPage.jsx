import { useEffect, useState } from "react";
import { useParams } from "react-router-dom"
import { postApi } from "../api/postApi";

export default () => {
  const {bno} = useParams();
  const [post ,setPost] = useState({});
  const [commentList, setCommentList] = useState([]);
  console.log(bno);
  useEffect(() => {
    postApi.getPost(bno).then(reponse => {
      setPost(reponse.data.board);
      setCommentList(reponse.data.commentList);
      console.log(reponse.data);
    }).catch(error=>{
      console.log(error);
    });
  },[]);

  return <div className="container">
    {
        !post ? <div className="post-loading">현재 게시글 읽어오고 있습니다.</div> :
    <>
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
        {commentList.map(item=><div className="comment-item">
          <div className="comment-info">
            <span>작성자 : {item.nickname}</span>
            <span>작성일 : {item.cdate}</span>
          </div>
          <div className="comment-action">
            <button>좋아요 👍</button>
            <button>싫어요 👎</button>

            <button>수정</button>
            <button>삭제</button>
          </div>
          <div className="comment-content">{item.content}</div>
        </div>)}
      </div>
    </div>
    </>
    }
  </div>
}