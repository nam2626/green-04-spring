import { useEffect, useState } from "react"
import { postApi } from "../api/postApi";

export default () => {
  const [posts, setPosts] = useState([]);
  const [pagging, setPagging] = useState({});
  // 게시글 첫번째 페이지 로드해서 출력
  useEffect(() => {
    postApi.getPage(1, '', 20)
    .then(response => {
        setPosts(response.data.list);
        setPagging(response.data.pagging);
        console.log(response.data);
      }
    )
  },[]);


  return <div className="container">
    <h2>게시글 목록</h2>
  </div>
}