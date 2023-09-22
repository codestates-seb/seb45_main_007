import { styled } from "styled-components";
import { Link, useNavigate } from "react-router-dom";
import modifyIcon from "../icon/modify.png";
import React, { useState, useEffect } from "react";
import userImg from "../images/userExample.png";
import axios from "axios";
import CategoryDropdown from "../components/CategoryDropdown.jsx";

export default function Mypage() {
  const [editing, setEditing] = useState(false);
  const [newNickname, setNewNickname] = useState("");
  const [nickname, setNickname] = useState("코딩왕");
  const [clubData, setClubData] = useState([]);
  const [clubPageInfo, setClubPageInfo] = useState({});
  const [marketData, setMarketData] = useState([]);
  const [marketPageInfo, setMarketPageInfo] = useState({});
  const [selectedCategory, setSelectedCategory] = useState("all");
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    setUsername(localStorage.getItem("username") || "이름 없음");
    setEmail(localStorage.getItem("email") || "이메일 없음");
    setNickname(localStorage.getItem("nickname") || "별명 없음");
    fetchData(1);
  }, [selectedCategory]);

  const memberId = localStorage.getItem("memberId");

  const handleDeleteAccount = async () => {
    try {
      const memberId = localStorage.getItem("memberId");
      if (!memberId) {
        alert("멤버 ID를 찾을 수 없습니다.");
        return;
      }

      const response = await axios.delete(
        `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/members/${memberId}`,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`, // 이 부분은 실제 토큰을 어떻게 저장하고 사용하는지에 따라 조정되어야 합니다.
          },
        },
      );

      if (response.status === 204) {
        // 204 No Content
        alert("회원 탈퇴가 성공적으로 처리되었습니다.");
        localStorage.clear();
        navigate("/");

        // 추가적인 로직 (예: 로그아웃 처리, 메인 페이지로 리다이렉트 등)을 여기에 추가할 수 있습니다.
      } else {
        alert("알 수 없는 오류가 발생했습니다.");
      }
    } catch (error) {
      if (error.response && error.response.status === 404) {
        alert("해당 멤버를 찾을 수 없습니다.");
      } else {
        alert("회원탈퇴 중 오류 발생");
        console.error("회원탈퇴 중 오류 발생", error);
      }
    }
  };

  const fetchData = async (page) => {
    try {
      const response = await axios.all([
        axios.get(
          `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/clubBoards/myPage/${memberId}?page=${page}&size=5&category=${selectedCategory}`,
          {
            headers: {
              "Content-Type": `application/json`,
              "ngrok-skip-browser-warning": "69420",
            },
          },
        ),
        axios.get(
          `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/marketBoards/myPage/${memberId}?page=${page}&size=5`,
          {
            headers: {
              "Content-Type": `application/json`,
              "ngrok-skip-browser-warning": "69420",
            },
          },
        ),
      ]);
      console.log(response);
      const clubResponse = response[0];
      const marketResponse = response[1];
      setClubData(clubResponse.data.clubBoards || []);
      setClubPageInfo(clubResponse.data.pageInfo || {});
      setMarketData(marketResponse.data.marketBoards || []);
      setMarketPageInfo(marketResponse.data.pageInfo || {});
    } catch (error) {
      console.error("Error fetching the data", error);
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

  const handleConfirmClick = async () => {
    try {
      const response = await axios.put(
        `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/members/${memberId}`,
        {
          nickname: newNickname,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
          },
        },
      );

      if (response.data.updated) {
        alert("닉네임이 성공적으로 변경되었습니다."); // 성공 메시지 출력
        setNickname(newNickname); // 변경된 닉네임 상태 업데이트
        localStorage.setItem("nickname", newNickname);
        setEditing(false); // 편집 모드 종료
      } else {
        alert(response.data.message); // 실패 메시지 출력
      }
    } catch (error) {
      if (error.response && error.response.data) {
        alert(error.response.data.message); // API에서 반환된 오류 메시지 출력
      } else {
        alert("닉네임 변경 중 오류 발생");
        console.error("닉네임 변경 중 오류 발생", error);
      }
    }
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
                  <td>{username}</td>
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
                  <td>{email}</td>
                </tr>
              </UserTable>
            </UserTableContainer>
          </UserInfo>
          <UserModify>
            <StyledLink to="/usermodify" className="modify">
              비밀번호변경
            </StyledLink>
            <StyledLink className="delete" onClick={handleDeleteAccount}>
              회원탈퇴
            </StyledLink>
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
                {marketData.map((post) => (
                  <tr key={post.markeyBoardId}>
                    <td>{post.marketBoardId}</td>
                    <td>{post.viewCount}</td>
                    <td>{post.tag}</td>
                    <td>{post.title}</td>
                    <td>{new Date(post.createdAt).toLocaleDateString()}</td>
                  </tr>
                ))}
              </tbody>
            </ContentsTable>
          </MiddleContents>
          {[...Array(marketPageInfo.totalPages)].map((_, i) => (
            <PageNumber onClick={() => handlePageClick(i + 1)} key={i}>
              {i + 1}
            </PageNumber>
          ))}
        </MiddleContainer>
        <BottomContainer>
          <BottomTitle>
            2. 내가 쓴 게시글
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
          {[...Array(clubPageInfo.totalPages)].map((_, i) => (
            <PageNumber onClick={() => handlePageClick(i + 1)} key={i}>
              {i + 1}
            </PageNumber>
          ))}
        </BottomContainer>
        <BottomContainer>
          <ButtonContianer>
            <Button onClick={() => navigate(-1)}>뒤로가기</Button>
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
