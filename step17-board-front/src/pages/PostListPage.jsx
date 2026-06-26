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
    <table>
      <thead>
        <tr>
          <th>글번호</th>
          <th>제목</th>
          <th>작성자</th>
          <th>작성일</th>
          <th>조회수</th>
          <th>좋아요</th>
          <th>싫어요</th>
        </tr>
      </thead>
      <tbody>
        {
          posts && posts.map(item => <tr>
            <td>{item.bno}</td>
            <td>{item.title}</td>
            <td>{item.nickname}</td>
            <td>{item.writeUpdateDate}</td>
            <td>{item.bcount}</td>
            <td>{item.blike}</td>
            <td>{item.bhate}</td>
          </tr>)

        }

      </tbody>
      <tfoot>
        <td colSpan={2}></td>
      </tfoot>
    </table>
  </div>
}