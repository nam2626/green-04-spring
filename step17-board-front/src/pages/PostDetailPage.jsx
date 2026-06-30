import { useEffect, useRef, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom"
import { postApi } from "../api/postApi";
import "quill/dist/quill.snow.css";
import { useAuth } from "../context/AuthContext";

export default () => {
  const {bno} = useParams();
  const [post ,setPost] = useState({});
  const [commentList, setCommentList] = useState([]);
  const {user} = useAuth();
  const commentForm = useRef(null);
  const navigate = useNavigate();

  // 댓글 수정을 위한 상태값
  const [commentEditMode, setCommentEditMode] = useState(0);
  const [commentEditContent, setCommentEditContent] = useState('');

  console.log(bno);
  useEffect(() => {
    postApi.getPost(bno).then(reponse => {
      setPost(reponse.data.board);
      setCommentList(reponse.data.commentList);
      console.log(reponse.data);
    }).catch(error=>{
      console.log(error);
    });
  },[bno]);
  const isEdit = user && (user.id === post.mid);

  const handleDelete = async () => {
    if(!window.confirm('정말로 삭제하시겠습니까?')) return ;
    try{
      await postApi.remove(bno);
      navigate('/');
    }catch(err){
      if(err.response?.status === 403){
        alert('삭제 권한이 없습니다.')
      }else{
        alert('삭제 실패 했습니다.');
      }
      console.log(err);
    }

  }

  const handleReaction = async (type) => {
    await postApi.postReaction({mid : user.id, type : type, bno : post.bno})
    .then(res => {
      console.log(res.data);
      setPost(prev => ({...prev, blike:res.data.count.likeCount, bhate:res.data.count.dislikeCount}))
    })
    .catch(err => console.log(err))
  }

  const handleAddComment = async () => {
    await postApi.addComment({bno : post.bno, content:commentForm.current.value})
    .then(res => {
      console.log(res.status);
      setCommentList(res.data.commentList);
    }).catch(err => {
      console.log(err.status)
      if(err.status == 401){
        alert('댓글 작성을 하실려면 로그인 하셔야합니다.');
        navigate('/login');
      }

      
    });
  }

  const handleDeleteComment = async (cno) => {
    await postApi.deleteComment(cno)
    .then(res => {
      setCommentList(res.data.commentList)
    })
    .catch(err => console.log(err));
  }

  const handleUpdateComment = async (cno) => {
    await postApi.updateComment({cno : cno, content:commentEditContent})
    .then(res => {
      console.log(res.status);
      setCommentList(res.data.commentList);
    }).catch(err => {
      console.log(err.status);      
    });
  }
  
  
  return <div className="post-detail-container">
    {
      !post ? <div className="post-loading">현재 게시글 읽어오고 있습니다.</div> :
      <>
        <h2 className="post-detail-title">{post.title}</h2>
        <div className="post-detail-meta">
          <span className="meta-item"><span className="meta-label">작성자</span> {post.nickname}</span>
          <span className="meta-item"><span className="meta-label">조회수</span> {post.bcount}</span>
          <span className="meta-item"><span className="meta-label">작성일</span> {post.writeUpdateDate}</span>
        </div>
        <div className="post-detail-content ql-container ql-snow" style={{border:'none'}}>
          <div dangerouslySetInnerHTML={{__html: post.content}}></div>
        </div>
        <div className="post-detail-footer">
          <div className="post-footer-group">
            <button className="btn btn-success-outline" onClick={() => handleReaction("LIKE")}>좋아요 👍 <span>{post.blike}</span></button>
            <button className="btn btn-danger-outline" onClick={() => handleReaction("DISLIKE")}>싫어요 👎 <span>{post.bhate}</span></button>
          </div>
            { 
              isEdit &&
              <div className="post-footer-group">
                  <button className="btn btn-secondary" onClick={() => navigate(`/posts/${post.bno}/edit`)}>수정</button>
                  <button className="btn btn-danger-outline" onClick={handleDelete}>삭제</button>
              </div>
            }
        </div>
        <div className="comment-section">
          <h3 className="comment-title">댓글 목록 ({commentList ? commentList.length : 0})</h3>
          {
            user ?   
            <div className="comment-form">
              <textarea className="comment-textarea" ref={commentForm} placeholder="댓글을 입력해 주세요."></textarea>
              <button className="comment-submit-btn" onClick={handleAddComment}>댓글<br/>등록</button>
            </div>
            :
            <div className="message-box">
              <Link to={"/login"}>댓글을 입력하실려면 로그인 하세요</Link>
            </div>
          }
          <div className="comment-list">
            {commentList && commentList.map((item, index) => <div key={item.cno || index} className="comment-item">
              <div className="comment-header">
                <div className="comment-info">
                  <span>👤 {item.nickname}</span>
                  <span>📅 {item.cdate}</span>
                </div>
                {
                item.cno == commentEditMode ?
                <div className="comment-action">
                  <button className="btn-comment-action">좋아요 👍 <span>{item.clike}</span></button>
                  <button className="btn-comment-action">싫어요 👎<span>{item.chate}</span></button>
                  {
                    user.id == item.mid &&
                    <>
                    <button className="btn-comment-action" onClick={() => setCommentEditMode(item.cno)}>수정</button>
                    <button className="btn-comment-action btn-comment-danger" onClick={() => handleDeleteComment(item.cno)}>삭제</button>
                    </>
                  }
                </div>
                  :
                    <div className="comment-form">
                      <textarea className="comment-textarea" placeholder="댓글을 입력해 주세요." onChange={(e) => setCommentEditContent(e.target.value)}>{item.content}</textarea>
                      <button className="comment-submit-btn" onClick={() => handleUpdateComment(cno)}>댓글<br/>수정</button>
                      <button className="comment-submit-btn" onClick={() => setCommentEditMode(0)}>취소</button>

                    </div>
                }
              </div>
              <div className="comment-content">{item.content}</div>
            </div>)}
          </div>
        </div>
      </>
    }
  </div>
}