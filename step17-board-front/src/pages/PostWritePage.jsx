import { useContext, useEffect, useState } from "react";
import QuillEditor from "../components/QuillEditor"
import { useNavigate, useParams } from "react-router-dom";
import { postApi } from "../api/postApi";


export default () => {
  const {bno} = useParams();
  
  // 수정인지? 작성인지?
  const isEditorMode = !!bno;
  
  const navigate = useNavigate();
  const [loading, setLoding] = useState(false);

  // 1. 제목, quill Editor 글내용 입력 받는 폼
  const [form, setForm] = useState({title:'',content:''});
  const [error, setError] = useState('');

  // 수정 모드이면 해당 게시글을 조회
  useEffect(() => {
    if(!isEditorMode) return;

    postApi.getPost(bno)
    .then((res) => {
      console.log(res.data);
      setForm(prev => ({...prev, title : res.data.board.title, content : res.data.board.content}))
    })
    .catch(err => {
      alert('게시글을 읽어오지 못했습니다.');
      navigate('/');
    });
   
  },[bno,isEditorMode]);

  
  const onChangePostDetail = (newPostDetail) => {
    setForm(prevForm => ({...prevForm, content : newPostDetail}));
  }
  
  const onChangeTitle = (e) => {
    setForm(prevForm => ({...prevForm, title : e.target.value}));
  }
  
  // 2. 글쓰기 버튼 클릭시 게시글 전송
  const handleSubmit = async () => {
    alert(form.title + " " + form.content);
    try{
      if(!form.title.trim() || !form.content.trim()){
        alert('제목과 내용을 입력해 주세요');
        return;
      }

      setLoding(true);
      if(isEditorMode){
        // 수정일때
        await postApi.update(bno,form);
        navigate(`/posts/${bno}`);
      }else{
        // 글쓰기 일때
        const res = await postApi.create(form);
        navigate(`/posts/${res.data.board.bno}`)
      }
    }catch(err){
      console.log(err);
    }finally{
      setLoding(false);
    }
  }

  return <div className="post-write-container">
    <div className="post-write-header">
      <h2 className="post-write-title">{isEditorMode ? '게시글 수정' : '게시글 쓰기'}</h2>
    </div>
    <div className="post-title">
      <label className="post-write-label">제목</label>
      <input className="post-title-input" type="text" placeholder="제목을 입력하세요" onChange={onChangeTitle} value={form.title}/>
    </div>
    <div className="post-detail">
      <label className="post-write-label">내용</label>
      <div id="editor" className="post-editor">
        <QuillEditor onChange={onChangePostDetail} defaultVale={isEditorMode ? form.content : ''}/>
      </div>
    </div>
    { error && <div className="error-box">{error}</div>}
    <div className="post-actions">
      <button className="btn btn-primary" onClick={handleSubmit} disabled={loading}>{
      loading ? '저장 중.....' : (isEditorMode ? '수정하기' : '글쓰기')}</button>
      <button className="btn btn-secondary" onClick={() => navigate(-1)}>취소</button>
    </div>
  </div>
}
