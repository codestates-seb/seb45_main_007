import styled from "styled-components";
import { Link } from "react-router-dom";
import modifyIcon from "../icon/modify.png";

import React, { useState, useEffect } from "react";
import { NewHeader } from "../components/NewHeader.jsx";
import userImg from "../images/userExample.png";
import axios from "axios";
import CategoryDropdown from "../components/CategoryDropdown.jsx";

export default function Mypage() {
  const [editing, setEditing] = useState(false);
  const [newNickname, setNewNickname] = useState("");
  const [nickname, setNickname] = useState("코딩왕");
  const [clubData, setClubData] = useState([]);
  const [pageInfo, setPageInfo] = useState({});
  const [selectedCategory, setSelectedCategory] = useState("");
  const fetchData = async (page) => {
    try {
      const response = await axios.get(
        `https://a742-2406-5900-705c-f80b-858d-ab44-7a5d-7fa2.ngrok-free.app/clubBoards/myPage/1?page=${page}&size=5&category=${selectedCategory}`,
        {
          headers: {
            "Content-Type": `application/json`,
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      setClubData(response.data.clubBoards || []);
      setPageInfo(response.data.pageInfo || {});
    } catch (error) {
      console.error("Error fetching the data", error);
      setClubData([]);
    }
  };
  useEffect(() => {
    fetchData(1);
  }, [selectedCategory]);

  const handlePageClick = (pageNum) => fetchData(pageNum);

  const handleEditClick = () => {
    setEditing(true);
    setNewNickname(nickname);
  };

  const handleConfirmClick = () => {
    setEditing(false);
    setNickname(newNickname);
  };

  const handleNicknameChange = (e) => {
    setNewNickname(e.target.value);
  };
  return (
    <>
      <Container>
        <Title>마 이 페 이 지</Title>
        <UserContainer>
          <UserInfo>
            <UesrInfoTitle>회 원 정 보</UesrInfoTitle>
            <UserTableContainer>
              <UserTable>
                <tr>
                  <th>이름</th>
                  <td>김코딩</td>
                </tr>
                <tr>
                  <th>별명</th>
                  <td className="nickname">
                    {editing ? (
                      <div>
                        <input
                          type="text"
                          value={newNickname}
                          onChange={handleNicknameChange}
                        />
                        <button className="icon" onClick={handleConfirmClick}>
                          확인
                        </button>
                      </div>
                    ) : (
                      <div>
                        {nickname}
                        <button className="icon" onClick={handleEditClick}>
                          <img src={modifyIcon} alt="modify" />
                        </button>
                      </div>
                    )}
                  </td>
                </tr>
                <tr>
                  <th>이메일</th>
                  <td>kimcoding@codestates.com</td>
                </tr>
              </UserTable>
            </UserTableContainer>
          </UserInfo>
          <UserModify>
            <StyledLink to="/usermodify" className="modify">
              비밀번호변경
            </StyledLink>
            <StyledLink className="delete">회원탈퇴</StyledLink>
          </UserModify>
          <UserImg>
            <img src={userImg} alt="userimage" />
          </UserImg>
        </UserContainer>
        <MiddleContainer>
          <MiddleTitle>
            1. 내가 쓴 판매글{" "}
            <div>
              <FilterButton>판매중</FilterButton>
              <FilterButton>판매완료</FilterButton>
            </div>
          </MiddleTitle>
          <MiddleContents>
            <ContentsTable>
              <thead>
                <tr>
                  <th width="100">번호</th>
                  <th width="150"> 조회수</th>
                  <th width="150"> 판매여부</th>
                  <th>글제목</th>
                  <th width="200">작성일자</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매완료</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
              </tbody>
            </ContentsTable>
          </MiddleContents>
          <PageNumber>1</PageNumber>
        </MiddleContainer>
        <MiddleContainer>
          <MiddleTitle>
            2. 내가 찜한 판매글{" "}
            <div>
              <FilterButton>판매중</FilterButton>
              <FilterButton>판매완료</FilterButton>
            </div>
          </MiddleTitle>
          <MiddleContents>
            <ContentsTable>
              <thead>
                <tr>
                  <th width="100">번호</th>
                  <th width="150"> 조회수</th>
                  <th width="150"> 판매여부</th>
                  <th>글제목</th>
                  <th width="200">작성일자</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매완료</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
                <tr>
                  <td>56</td>
                  <td>324</td>
                  <td>판매중</td>
                  <td>안녕하세요</td>
                  <td>2023.08.23</td>
                </tr>
              </tbody>
            </ContentsTable>
          </MiddleContents>
          <PageNumber>1</PageNumber>
        </MiddleContainer>
        <BottomContainer>
          <BottomTitle>
            3. 내가 쓴 게시글
            <CategoryDropdown
              setSelectedCategory={setSelectedCategory}
              fetchData={fetchData}
            />
          </BottomTitle>
          <BottomContents>
            <ContentsTable>
              <thead>
                <tr>
                  <th width="100">번호</th>
                  <th width="150"> 조회수</th>
                  <th width="150">카테고리</th>
                  <th>글제목</th>
                  <th width="200">작성일자</th>
                </tr>
              </thead>
              <tbody>
                {clubData.map((post) => (
                  <tr key={post.clubBoardId}>
                    <td>{post.clubBoardId}</td>
                    <td>{post.viewCount}</td>
                    <td>{post.category}</td>
                    <td>{post.title}</td>
                    <td>{new Date(post.createdAt).toLocaleDateString()}</td>
                  </tr>
                ))}
              </tbody>
            </ContentsTable>
          </BottomContents>
          {[...Array(pageInfo.totalPages)].map((_, i) => (
            <PageNumber onClick={() => handlePageClick(i + 1)} key={i}>
              {i + 1}
            </PageNumber>
          ))}
        </BottomContainer>
        <BottomContainer>
          <ButtonContianer>
            <Button>뒤로가기</Button>
          </ButtonContianer>
        </BottomContainer>
      </Container>
    </>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  margin: 10% 0;
`;
const Title = styled.h1`
  font-size: 36px;
  font-family: "ChosunKm";
  margin-bottom: 50px;
`;
const UserContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 75%;
  margin-top: 50px;
`;

const UserInfo = styled.div`
  display: flex;
  justify-content: center;
  width: 60%;
  height: 200px;
  border: 1px solid;
  margin-right: 50px;
  flex-direction: column;
`;

const UserImg = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 200px;
  width: 175px;
  border: 1px solid;
  margin-left: 50px;
  img {
    width: 100%;
    height: 100%;
  }
`;

const UesrInfoTitle = styled.div`
  width: 100%;
  border: 1px solid;
  display: flex;
  justify-content: center;
  height: 30px;
  font-family: "ChosunKm";
  align-items: center;
  font-size: 20px;
  font-weight: bold;
  background-color: lightgray;
`;

const UserTableContainer = styled.div`
  width: 100%;
  height: 180px;
  display: flex;
  justify-content: center;
`;

const UserTable = styled.table`
  width: 100%;
  border-collapse: collapse;
  th {
    font-family: "ChosunKm";
    border: 1px solid;
    height: 50px;
    background-color: lightgrey;
    font-size: 20px;
  }
  td {
    border: 1px solid;
    font-family: "Hangeuljaemin4-Regular";
    text-align: center;
    font-size: 20px;
    padding: 0;
  }
  .nickname {
    position: relative;
    font-family: "Hangeuljaemin4-Regular";
  }
  .icon {
    position: absolute;
    right: 0;
    top: 0;
    bottom: 0;
    display: flex;
    align-items: center;
    padding-right: 10px;
    background-color: white;
    border: none;
    font-family: "Hangeuljaemin4-Regular";
    font-size: 20px;
    &:hover {
      cursor: pointer;
    }
  }
  div:first-child {
    display: inline-block;
    vertical-align: middle;
  }
  input {
    font-family: "Hangeuljaemin4-Regular";
    font-size: 20px;
    &:focus {
      outline: none;
    }
  }
`;

const MiddleContainer = styled.div`
  width: 75%;
  margin-top: 50px;
`;

const MiddleTitle = styled.h2`
  font-size: 28px;
  font-family: "ChosunKm";
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  margin-bottom: 50px;
`;

const MiddleContents = styled.div`
  border: 1px solid;
  display: flex;
  height: 300px;
`;

const PageNumber = styled.div`
  width: 100%;
  height: 28px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "ChosunKm";
  padding-top: 50px;
  text-decoration: underline;
  font-weight: bold;
  flex-direction: row;
`;

const BottomContainer = styled(MiddleContainer)``;

const BottomTitle = styled(MiddleTitle)``;

const BottomContents = styled(MiddleContents)``;

const ContentsTable = styled.table`
  width: 100%;
  border-collapse: collapse;
  th {
    font-family: "ChosunKm";
    border: 1px solid;
    height: 50px;
    background-color: lightgrey;
    font-size: 20px;
  }
  td {
    border: 1px solid;
    font-family: "Hangeuljaemin4-Regular";
    text-align: center;
    font-size: 20px;
  }
`;
const UserModify = styled.div`
  width: 15%;
  display: flex;
  justify-content: space-between;
  span {
    text-decoration: underline;
    font-family: "ChosunKm";
    font-weight: 100;
  }
  .delete {
    color: #d32020;
  }
`;
const StyledLink = styled(Link)`
  text-decoration: underline;
  font-family: "ChosunKm";
  font-size: 16px;
  font-weight: 100;
  color: black;
  .delete {
    color: #d32020;
  }
`;
const ButtonContianer = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-around;
`;
const FilterButton = styled.button`
  border: none;
  font-family: "ChosunKm";
  font-size: 16px;
  border: 1px solid;
  &:hover {
    cursor: pointer;
  }
`;

const Button = styled.button`
  width: 155px;
  height: 48px;
  border-radius: 12px;
  background-color: #ffffff;
  font-family: "ChosunKm";
  font-size: 20px;
  &:hover {
    cursor: pointer;
    background-color: #f0f0f0f0;
  }
`;
