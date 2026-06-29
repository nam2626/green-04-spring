import Quill from "quill";
import "quill/dist/quill.snow.css";
import { useEffect, useRef } from "react";

export default ({onChange, defaultVale}) => {
  // 에디터를 가리킬 Ref
  const editorRef = useRef(null);
  const quillInstance = useRef(null);
  const initializedRef = useRef(false);
  const onChangeRef = useRef(onChange);

  useEffect(() => {
    onChangeRef.current = onChange;
  }, [onChange]);

  useEffect(() => {
    if(editorRef.current && !quillInstance.current){
      quillInstance.current = new Quill(editorRef.current,{
        theme:'snow',
        modules : {
          toolbar : [['bold', 'italic', 'underline', 'strike'],        // toggled buttons
          ['blockquote', 'code-block'],
          ['link', 'image', 'video', 'formula'],
      
          [{ 'header': 1 }, { 'header': 2 }],               // custom button values
          [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
          [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
          [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
          [{ 'direction': 'rtl' }],                         // text direction
      
          [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
          [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
      
          [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
          [{ 'font': [] }],
          [{ 'align': [] }],
      
          ['clean'] ]
        }
    });

      // 내용이 변경될 때 실행할 이벤트 리스너 등록
      quillInstance.current.on('text-change', () => {
        if(onChangeRef.current){
          console.log(quillInstance.current.getSemanticHTML());
          onChangeRef.current(quillInstance.current.getSemanticHTML());
        }
      });
    }
  },[]);

  useEffect(() => {
    if(!quillInstance.current || !defaultVale || initializedRef.current) return;

    // 초기값 설정. 수정모드일때 이전에 입력했던 게시글 값
    quillInstance.current.root.innerHTML = defaultVale;

    const length = quillInstance.current.getLength();
    quillInstance.current.setSelection(Math.max(length - 1, 0), 0);
    initializedRef.current = true;
  },[defaultVale]);
 

  return (
    <div style={{margin:'50px'}}>
      <div ref={editorRef} style={{height:'500px'}}></div>      
    </div>
  );
}
