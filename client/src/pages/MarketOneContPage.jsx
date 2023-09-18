import { styled } from "styled-components";
import Reply from "../components/Reply.jsx";
// import nextIcon from "../icon/next.png";
// import preIcon from "../icon/pre.png";
// import { HotContent } from "../components/HotContent.jsx";
import React, { useEffect, useState } from "react";
import { NewHeader } from "../components/NewHeader.jsx";
import charImg from "../images/userExample.png";
import axios from "axios";
import IsSameDay from "../utility/IsSameDay.jsx";
import { Link, useNavigate } from "react-router-dom";
export default function MarketOneContPage() {
  const [data, setData] = useState({});
  const navigate = useNavigate();
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "https://49c9-221-150-55-48.ngrok-free.app/marketBoards/13",
          {
            headers: {
              "Content-Type": `application/json`,
              "ngrok-skip-browser-warning": "69420",
            },
          },
        );
        console.log(response);
        setData(response.data);
      } catch (error) {
        console.error("Error fetching the data", error);
      }
    };

    fetchData();
  }, []);

  const deletePost = async () => {
    if (window.confirm("정말로 이 게시글을 삭제하시겠습니까?")) {
      try {
        const response = await axios.delete(
          "https://49c9-221-150-55-48.ngrok-free.app/marketBoards/13",
          { headers: { "Content-Type": "application/json" } },
        );
        console.log(response);
        if (response.status === 200) {
          navigate("/"); // 홈페이지로 리디렉션
        }
      } catch (error) {
        console.error("Error deleting the post", error);
      }
    }
  };

  return (
    <>
      <NewHeader />
      <TotalContainer>
        <BoardNoteContainer>
          <ContentContainer>
            <Margnet />
            <ContentTitle>{data.title}</ContentTitle>
            <SecondContainer>
              <CreatedAt>{IsSameDay(data.createdAt)}</CreatedAt>
              <WriterContainer>
                {data.nickname}
                <WriterImage>
                  <img src={charImg} alt="user" />
                </WriterImage>
              </WriterContainer>
            </SecondContainer>
            <ContentImage>
              {/* <Icon className="pre">
                <img src={preIcon} alt="previous" />
              </Icon> */}
              <img
                src={data.photo}
                alt="mdExample"
                style={{ height: "650px" }}
              />
              {/* <Icon className="next">
                <img src={nextIcon} alt="next" />
              </Icon> */}
            </ContentImage>
            <TextContentContainer>
              <Price>{data.priceContent}원</Price>
              <TextContent>{data.content}</TextContent>
            </TextContentContainer>
            <BottomContainer>
              <div className="left">
                <div>조회수 : {data.viewCount}</div> <div>찜 : 20</div>{" "}
                <div>댓글 : 3</div>
              </div>
              <div>
                <StyledLink className="vote">♡ 찜하기</StyledLink>
                <StyledLink>수정</StyledLink>{" "}
                <StyledLink onClick={deletePost}>삭제</StyledLink>
              </div>
            </BottomContainer>
          </ContentContainer>
          <Reply />
          {/* <HotContent /> */}
        </BoardNoteContainer>
      </TotalContainer>
    </>
  );
}

const TotalContainer = styled.div`
  width: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  margin-bottom: 10%;
  margin-top: 10vh;
`;

const BoardNoteContainer = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  background: #083d03;
  margin-top: 0.2%;
  box-sizing: border-box;
  border-top: 20px solid brown;
  border-bottom: 20px solid brown;
  justify-content: center;
  align-items: center;
  position: relative;
`;
const Margnet = styled.div`
  width: 30px;
  height: 30px;
  background-color: red;
  border-radius: 15px;
  box-shadow: 2px 2px 2px 2px gray;
  position: absolute;
  top: -20px;
  left: 50%;
  z-index: 998;
`;

const ContentContainer = styled.div`
  width: 75%;
  border: 1px solid;
  background-color: white;
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  box-shadow: 3px 3px 3px 3px black;
  margin-top: 50px;
  position: relative;
`;

const ContentImage = styled.div`
  width: 100%;
  margin-top: 30px;
  margin-bottom: 30px;
  display: flex;
  justify-content: center;
  align-items: center;

  img {
    width: 80%;
    height: 80%;
    border-radius: 12px;
  }
`;
// const Icon = styled.div`
//   width: 30px;
//   height: 30px;
//   display: flex;
//   justify-content: center;
//   &:hover {
//     cursor: pointer;
//     filter: opacity(0.5) drop-shadow(0 0 0 gray);
//   }
// `;

const WriterContainer = styled.div`
  display: flex;
  width: 100%;
  height: 25px;
  align-items: center;
  justify-content: end;
  font-size: 16px;
`;

const WriterImage = styled.div`
  width: 50px;
  height: 50px;
  margin-left: 30px;
  img {
    width: 100%;
    height: 100%;
    border-radius: 30px;
  }
`;
const SecondContainer = styled.div`
  width: 80%;
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid black;
  padding-bottom: 20px;
`;
const TextContentContainer = styled.div`
  width: 80%;
  padding-bottom: 20px;
  line-height: 1.5;
`;
const ContentTitle = styled.h2`
  width: 80%;
  margin-top: 15px;
  margin-bottom: 15px;
  border-bottom: 2px solid black;
  height: 75px;
  font-size: 22px;
  display: flex;
  align-items: center;
`;
const CreatedAt = styled.div`
  width: 100%;
  color: #756e6e;
  display: flex;
  align-items: center;
  font-size: 16px;
`;
const Price = styled.h3`
  width: 100%;
  margin-top: 10px;
  margin-bottom: 30px;
  font-size: 32px;
`;
const TextContent = styled.div`
  width: 100%;
  font-size: 16px;
`;
const BottomContainer = styled.div`
  width: 80%;
  color: #756e6e;
  font-size: 16px;
  display: flex;
  align-items: center;
  margin-bottom: 30px;
  padding-top: 15px;
  border-top: 1px solid gray;
  justify-content: space-between;
  div {
    padding-right: 20px;
    margin-right: 15px;
  }
  .left {
    display: flex;
    flex-direction: row;
  }
`;
const StyledLink = styled(Link)`
  text-decoration: none;
  margin-left: 15px;
  padding-left: 20px;
  color: #756e6e;
  &.vote {
    color: #f397a6;
  }
`;
