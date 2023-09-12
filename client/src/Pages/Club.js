import { styled } from "styled-components";

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

const ClubPage = styled.div`
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  border: 1px solid black;
`;
const PageContent = styled.div`
  width: 1440px;
  display: flex;
  justify-content: center;
  border: 1px solid black;
`;

const CategorySelection = styled.div`
  width: 10%;
  display: flex;
  flex-direction: column;
  margin-right: 20px;
  border: 1px solid black;
`;

const CategoryButton = styled.button`
  margin-bottom: 10px;
`;

const CategoryBoxes = styled.div`
  width: 90%;
  display: flex;
  flex-wrap: wrap;
  gap: 50px;
  border: 1px solid black;
  justify-content: center;
`;

const CategoryBox = styled.div`
  width: 400px;
  height: 200px;
  border: 1px solid #000;
  padding: 10px;
`;

const CategoryHeader = styled.div`
  display: flex;
  font-size: 20px;
  justify-content: space-between;
  margin-bottom: 10px;
  border: 1px solid black;
`;

const CategoryContent = styled.div``;

const Post = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
`;

function Club() {
  return (
    <ClubPage>
      <PageContent>
        <CategorySelection>
          <div>카테고리</div>
          {categories.map((category) => (
            <CategoryButton
              key={category.name}
              onClick={() => (window.location.href = category.path)}
            >
              {category.name}
            </CategoryButton>
          ))}
        </CategorySelection>

        <CategoryBoxes>
          {categories.map((category) => (
            <CategoryBox key={category.name}>
              <CategoryHeader>
                <span>{category.name}</span>
                <button onClick={() => (window.location.href = category.path)}>
                  +
                </button>
              </CategoryHeader>
              <CategoryContent>
                {posts.map((post, index) => (
                  <Post key={index}>
                    <span>{post.title}</span>
                    <div>
                      <span>추천수: {post.likes}</span>
                      <span>{post.date}</span>
                    </div>
                  </Post>
                ))}
              </CategoryContent>
            </CategoryBox>
          ))}
        </CategoryBoxes>
      </PageContent>
    </ClubPage>
  );
}

export default Club;
