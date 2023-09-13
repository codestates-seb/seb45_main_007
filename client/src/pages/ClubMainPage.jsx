import { styled } from "styled-components";
import { React } from "react";
import { NewHeader } from "../components/NewHeader.jsx";
import { BoardFilter } from "../components/BoardFilter.jsx";

const categories = [
  { name: "만화", path: "/comics" },
  { name: "영화", path: "/movies" },
  { name: "TV 프로그램", path: "/tvshows" },
  { name: "추억템", path: "/nostalgiaitems" },
  { name: "노래", path: "/songs" },
  { name: "게임", path: "/games" },
  { name: "기타", path: "/others" },
];

const posts = [
  { title: "게시글 1", likes: 10, date: "2023-09-11" },
  { title: "게시글 2", likes: 20, date: "2023-09-12" },
  // ... (다른 게시글들)
];

const ClubTotalContainer = styled.section`
  width: 100vw;
  height: auto;
  display: flex;
  margin-top: 10vh;
`;

// const HeaderBox = styled.div`
//   width: 100%;
//   height: 92px;
//   border: 3px solid black;
// `;

const PageContent = styled.div`
  width: 1440px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background: #587c55;
  border: 20px solid #a52a2a9d;
  position: relative;
`;

const SchoolBoardTitle = styled.section`
  width: 100%;
  height: 75px;
  display: flex;
  align-items: center;
  justify-content: center;
  top: 0;
  margin-top: 10px;
  margin-bottom: 10px;
`;

const SchoolBCircle = styled.div`
  width: 70px;
  height: 70px;
  background-color: white;
  margin-left: 50px;
  border-radius: 50%;
  font-size: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 5px solid pink;
`;

const CategoryBoxes = styled.div`
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 50px;
  justify-content: center;
  margin-top: 50px;
  margin-bottom: 50px;
`;

const CategoryBox = styled.div`
  width: 500px;
  height: 250px;
  border: 1px solid #fabcbc;
  padding: 10px;
  background: #ffffff;
  border-radius: 8px;
`;

const CategoryHeader = styled.div`
  display: flex;
  font-size: 20px;
  justify-content: space-between;
  margin-bottom: 10px;
  border-bottom: 3px solid black;
  height: 30px;
  align-items: center;
`;
const PlusButton = styled.button`
  width: 30px;
  height: 100%;
  margin-bottom: 10px;
  border: none;
  font-size: 32px;
  background-color: white;
  cursor: pointer;
`;

const CategoryContent = styled.div`
  margin-top: 20px;
`;

const Post = styled.div`
  font-size: 13px;
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  align-items: center;
  height: 35px;
  line-height: 1.2;
  border-bottom: 1px solid gray;
`;

const ClubMainFooterSect = styled.div`
  width: 100%;
  height: 10vh;
  background-color: #fffff0;
`;

function ClubMainPage() {
  return (
    <>
      <NewHeader />
      <ClubTotalContainer>
        <BoardFilter />
        <PageContent>
          <SchoolBoardTitle>
            <SchoolBCircle>학</SchoolBCircle>
            <SchoolBCircle>급</SchoolBCircle>
            <SchoolBCircle>게</SchoolBCircle>
            <SchoolBCircle>시</SchoolBCircle>
            <SchoolBCircle>판</SchoolBCircle>
          </SchoolBoardTitle>
          <CategoryBoxes>
            {categories.map((category) => (
              <CategoryBox key={category.name}>
                <CategoryHeader>
                  <span>{category.name}</span>
                  <PlusButton
                    onClick={() => (window.location.href = category.path)}
                  >
                    +
                  </PlusButton>
                </CategoryHeader>
                <CategoryContent>
                  {posts.map((post, index) => (
                    <Post key={index}>
                      <div>{post.title}</div>
                      <div>
                        <div>추천수: {post.likes}</div>
                        <div>{post.date}</div>
                      </div>
                    </Post>
                  ))}
                </CategoryContent>
              </CategoryBox>
            ))}
          </CategoryBoxes>
        </PageContent>
      </ClubTotalContainer>
      <ClubMainFooterSect />
    </>
  );
}

export default ClubMainPage;

{
  /* <CategorySelection>
          <CategoryDiv>카테고리</CategoryDiv>
          {categories.map((category) => (
            <CategoryButton
              key={category.name}
              onClick={() => (window.location.href = category.path)}
            >
              {category.name}
            </CategoryButton>
          ))}
        </CategorySelection> */
}
