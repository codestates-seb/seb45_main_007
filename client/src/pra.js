// import React, { useState, useEffect } from 'react';

// const InfiniteScroll = () => {
//   const [data, setData] = useState([]);
//   const [isLoading, setIsLoading] = useState(false);

//   useEffect(() => {
//     // 초기 데이터 로드
//     loadMoreData();
//   }, []);

//   const loadMoreData = () => {
//     // 데이터 로딩 중이면 아무 작업도 수행하지 않음
//     if (isLoading) return;

//     // 데이터 로딩 중 상태로 변경
//     setIsLoading(true);

//     // 새로운 데이터를 가져오는 비동기 작업
//     fetchData()
//       .then(newData => {
//         // 가져온 데이터를 현재 데이터와 병합
//         setData(prevData => [...prevData, ...newData]);
//         setIsLoading(false); // 로딩 상태 해제
//       })
//       .catch(error => {
//         console.error('데이터 로드 오류:', error);
//         setIsLoading(false); // 오류 발생 시 로딩 상태 해제
//       });
//   };

//   // 스크롤 이벤트를 감지하여 loadMoreData 함수 호출
//   window.addEventListener('scroll', () => {
//     if (
//       window.innerHeight + window.scrollY >= document.body.offsetHeight - 100 &&
//       !isLoading
//     ) {
//       loadMoreData();
//     }
//   });

//   // fetchData 함수는 실제로 데이터를 가져오는 비동기 함수로 대체해야 합니다.

//   return (
//     <div>
//       {data.map(item => (
//         <div key={item.id}>{item.text}</div>
//       ))}
//       {isLoading && <div>Loading...</div>}
//     </div>
//   );
// };

// export default InfiniteScroll;

// // Board.js (게시물 목록 페이지)

// import React from 'react';
// import { Link } from 'react-router-dom';

// // 게시물 목록 데이터 가져오기
// const posts = [
//   { id: 1, title: '게시물 1' },
//   { id: 2, title: '게시물 2' },
//   // ...
// ];

// function Board() {
//   return (
//     <div>
//       <h1>게시판</h1>
//       <ul>
//         {posts.map(post => (
//           <li key={post.id}>
//             <Link to={`/post/${post.id}`}>{post.title}</Link>
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// }

// export default Board;

// 페이지네이션 관련 코드 로직 분석

// 페이지네이션과 관련해서, 중요한 것은 현재 페이지에 어떤 컨텐츠를 표시할지에 대하여 현재 페이지의 번호에 따라 코드가 판단해야 한다는 점이다.

// 예를 들어 1번 페이지라고 했을 경우에, 하나의 페이지에 10개의 게시물을 보여준다고 하면, 그 게시물들만 필터링(슬라이스)해야 한다. 이것은 슬라이스로 판단을 하는데, 슬라이스의 기준을 시작 인덱스와 끝 인덱스로 잡아서 슬라이스를 한다.

// 그렇다면 이것을 어디에 쓰면 될까? 현재의 페이지 번호는 상태이다. 어떤 api를 받아와서 배열의 데이터를 하나의 변수에 저장했다고 하자. 그것을 불러와서 상태에 맞게 자르면 된다. 그렇다면 상태를 정의하고, 현재 사용자가 어떤 상태에 있는지 판단하고, 버튼을 누를 때마다 상태가 바뀌고, 그 상태에 따라 게시물 목록이 바뀌면 된다는 것이다.

// 이 정도만 기획하고 생각하면 되겠는가? 라우팅은 필요없나? 이 정도만 하면 기본 기능에 대한 테스트는 가능한가? 다른 버튼이 있다면?

// 페이지 버튼을 생성하는 코드

// <div>
//         {Array.from({ length: totalPages }, (_, index) => (
//           <button key={index + 1} onClick={() => handlePageChange(index + 1)}>
//             {index + 1}
//           </button>
//         ))}
//       </div>
// ================================================

// import React, { useState } from "react";

// function CommentSection() {
//   const [comments, setComments] = useState([
//     {
//       id: 1,
//       text: "첫 번째 댓글",
//       replies: [],
//     },
//   ]);
//   // 댓글을 담고 있는 배열을 만든다. 여기서 하나의 객체는 하나의 댓글이라 생각하면 된다. replies는 댓글에 대한 대댓글이다.
//   const [newComment, setNewComment] = useState("");
//   // 새로운 댓글을 입력받는 상태값인것 같다.

//   const addComment = () => {
//     if (newComment.trim() !== "") {
//       const newCommentObj = {
//         id: comments.length + 1,
//         text: newComment,
//         replies: [],
//       };
//       // 새로운 배열을 하나 만들어서
//       setComments([...comments, newCommentObj]);
//       // 기존 댓글 배열에 추가한다.
//       setNewComment("");
//       // 그리고 새로운 댓글을 입력받는 상태값을 초기화한다.
//     }
//   };

//   // 새로운 댓글을 추가하는 함수, 상태값 비우기와 추가하기.

//   const addReply = (parentId, replyText) => {
//     const updatedComments = comments.map((comment) => {
//       if (comment.id === parentId) {
//         const newReply = {
//           id: comment.replies.length + 1,
//           text: replyText,
//         };
//         return {
//           ...comment,
//           replies: [...comment.replies, newReply],
//         };
//       }
//       return comment;
//     });

//     setComments(updatedComments);
//   };

//   // 대댓글을 추가하는 기능이다. 부모의 위치를 찾아서, 새로운 대댓글을 더해나간다. 이때 대댓글 하나는 객체로 이루어져 있는데, 그 아이디 값이 대댓글의 뎁스값에 해당한다.
//   return (
//     <div>
//       <div>
//         <h2>댓글 목록</h2>
//         <ul>
//           {comments.map((comment) => (
//             <li key={comment.id}>
//               {comment.text}
//               <button onClick={() => addReply(comment.id, "대댓글 추가")}>
//                 대댓글 달기
//               </button>
//               <ul>
//                 {comment.replies.map((reply) => (
//                   <li key={reply.id}>{reply.text}</li>
//                 ))}
//               </ul>
//             </li>
//           ))}
//         </ul>
//       </div>

//       {/* 이 ul-li 구조가 중요한데, 하나의 댓글을 보여주면서 옆에 대댓글 추가하는 버튼이 존재한다. 그리고 대댓글을 하나의 뎁스씩 아이디 대로 차례대로 보여주고 있다. css만 조정하면 되고, 뎁스는 설정되어 있다. 그러나 이것은 대댓글의 개념을 제대로 보여주지 못하고 있는 것 같다. 대댓글은 댓글이 하나의 댓글이라고 하자. 이때 대댓글이 3개 존재한다고 하자. 그렇다면 각각의 대댓글에 또 댓글을 달 수 있어야 한다. 각각이 다시 댓글을 가지고 잇어야 한다는 것이다. */}

//       <div>
//         <h2>댓글 달기</h2>
//         <textarea
//           rows="3"
//           placeholder="댓글을 입력하세요."
//           value={newComment}
//           onChange={(e) => setNewComment(e.target.value)}
//         ></textarea>
//         <button onClick={addComment}>댓글 달기</button>
//       </div>
//     </div>
//   );
// }

// export default CommentSection;

// import React, { useState } from 'react';

// function CommentSection() {
//   const [comments, setComments] = useState([
//     {
//       id: 1,
//       text: '첫 번째 댓글',
//       parentId: null,
//     },
//   ]);
//   const [newComment, setNewComment] = useState('');
//   const [replyTo, setReplyTo] = useState(null);

//   const addComment = () => {
//     if (newComment.trim() !== '') {
//       const newCommentObj = {
//         id: comments.length + 1,
//         text: newComment,
//         parentId: null, // 댓글의 parentId는 null로 설정
//       };

//       setComments([...comments, newCommentObj]);
//       setNewComment('');
//     }
//   };

//   const addReply = () => {
//     if (newComment.trim() !== '') {
//       const newReply = {
//         id: comments.length + 1,
//         text: newComment,
//         parentId: replyTo, // 대댓글의 parentId는 선택한 댓글의 ID
//       };

//       // 새로운 대댓글을 추가하고 replyTo 상태 초기화
//       setComments([...comments, newReply]);
//       setReplyTo(null);
//       setNewComment('');
//     }
//   };

//   return (
//     <div>
//       <div>
//         <h2>댓글 목록</h2>
//         <ul>
//           {comments.map((comment) => (
//             <li key={comment.id}>
//               {comment.text}
//               <button onClick={() => setReplyTo(comment.id)}>대댓글 달기</button>
//               <ul>
//                 {comments
//                   .filter((reply) => reply.parentId === comment.id)
//                   .map((reply) => (
//                     <li key={reply.id}>{reply.text}</li>
//                   ))}
//               </ul>
//             </li>
//           ))}
//         </ul>
//       </div>

//       <div>
//         <h2>댓글 달기</h2>
//         <textarea
//           rows="3"
//           placeholder="댓글을 입력하세요."
//           value={newComment}
//           onChange={(e) => setNewComment(e.target.value)}
//         ></textarea>
//         {replyTo ? (
//           <button onClick={addReply}>대댓글 작성</button>
//         ) : (
//           <button onClick={addComment}>댓글 작성</button>
//         )}
//       </div>
//     </div>
//   );
// }

// export default CommentSection;

// import React, { useState, useEffect } from 'react';

// function PostList() {
//   const [posts, setPosts] = useState([]);

//   useEffect(() => {
//     // 백엔드 API에서 게시물 목록을 가져오는 함수 (실제로는 백엔드 서버의 URL을 사용해야 합니다)
//     const fetchPosts = async () => {
//       try {
//         const response = await fetch('/api/posts'); // API 엔드포인트 URL을 사용

//         if (!response.ok) {
//           throw new Error('게시물 목록을 가져오는 데 실패했습니다.');
//         }

//         const data = await response.json();
//         setPosts(data); // 게시물 목록을 상태에 저장
//       } catch (error) {
//         console.error(error);
//       }
//     };

//     fetchPosts(); // 게시물 목록을 가져오는 함수 호출
//   }, []);

//   return (
//     <div>
//       <h1>게시물 목록</h1>
//       <ul>
//         {posts.map((post) => (
//           <li key={post.id}>
//             <h2>{post.title}</h2>
//             <p>작성자: {post.author}</p>
//             <p>{post.content}</p>
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// }

// export default PostList;

// 데이터 목록을 상태로 관리하는 이유는 리액트 컴포넌트의 렌더링과 상호작용을 관리하고, 컴포넌트의 동적 업데이트를 효율적으로 처리하기 위해서입니다. 여러 이유로 상태로 데이터를 관리하는 것이 권장됩니다.

// 렌더링 관리: 상태로 데이터를 관리하면 컴포넌트가 데이터 변경에 따라 자동으로 리렌더링됩니다. 이것은 데이터가 업데이트될 때 UI를 자동으로 업데이트할 수 있게 해주며, 개발자가 직접 DOM 조작을 할 필요가 없어집니다.

// 컴포넌트 간 데이터 공유: 리액트 컴포넌트는 데이터를 상태로 관리하므로 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달하기 쉽습니다. 데이터를 상태로 관리하면 컴포넌트 간에 데이터를 공유하거나 전달하기 쉽게 되며, 데이터 흐름을 관리하기 용이해집니다.

// 컴포넌트의 재사용성: 상태로 데이터를 관리하면 데이터에 독립적인 컴포넌트를 작성할 수 있습니다. 이렇게 하면 같은 컴포넌트를 여러 곳에서 재사용할 수 있으며, 데이터의 종속성을 최소화할 수 있습니다.

// 컴포넌트의 상호작용: 사용자와의 상호작용에 따라 데이터가 변경될 때 상태로 관리하면 컴포넌트의 상태 변화를 추적하고 업데이트할 수 있습니다. 예를 들어, 사용자가 입력 폼에 데이터를 입력하면 상태를 업데이트하고, 해당 데이터를 서버로 전송하는 등의 작업을 처리할 수 있습니다.

// 라이프사이클 관리: 상태로 데이터를 관리하면 리액트의 라이프사이클 메서드와 함께 사용할 수 있습니다. 이를 통해 데이터의 초기화, 업데이트, 제거 등을 더욱 효과적으로 관리할 수 있습니다.

// 요약하면, 데이터를 상태로 관리하면 리액트 애플리케이션의 렌더링, 상호작용, 상태 변화 등을 효율적으로 다룰 수 있으며, 코드의 가독성과 유지보수성을 향상시킬 수 있습니다.

// import React from 'react';
// import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
// import Board from './Board'; // 게시판 컴포넌트
// import PostDetail from './PostDetail'; // 게시물 상세 정보 컴포넌트

// const App = () => {
//   return (
//     <Router>
//       <Switch>
//         <Route path="/board" exact component={Board} />
//         <Route path="/board/:postId" component={PostDetail} />
//       </Switch>
//     </Router>
//   );
// };

// export default App;

// import React from 'react';
// import { Link } from 'react-router-dom';

// const Board = () => {
//   // 게시물 목록과 게시물 아이디를 가지고 있는 배열
//   const posts = [
//     { id: 1, title: '게시물 1' },
//     { id: 2, title: '게시물 2' },
//     // ...
//   ];

//   return (
//     <div>
//       <h1>게시판</h1>
//       <ul>
//         {posts.map(post => (
//           <li key={post.id}>
//             <Link to={`/board/${post.id}`}>{post.title}</Link>
//           </li>
//         ))}
//       </ul>
//     </div>
//   );
// };

// export default Board;

// import React from 'react';
// import { useParams } from 'react-router-dom';

// const PostDetail = () => {
//   const { postId } = useParams();

//   // postId를 사용하여 게시물 정보를 가져옴

//   return (
//     <div>
//       <h2>게시물 상세 정보</h2>
//       <p>게시물 아이디: {postId}</p>
//       {/* 게시물 상세 정보 표시 */}
//     </div>
//   );
// };

// export default PostDetail;
