import { styled } from "styled-components";
import { NewHeader } from "../components/NewHeader.jsx";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";

const BoardOneContContainer = styled.section`
  width: 100%;
  display: flex;
  margin-bottom: 10vh;
`;

const BoardOneFilterSect = styled.article`
  width: 12.5%;
  height: 100vh;
  margin-top: 7.2vh;
`;

const BoardOneContentSect = styled.article`
  width: 75%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 7.2vh;
`;

const BoardUpSect = styled.div`
  width: 100%;
  height: 10vh;
  margin-top: 50px;
  display: flex;
  position: relative;
`;

const PrevBoardBtn = styled.div`
  width: 100px;
  height: 50px;
  border-radius: 15px;
  background-color: #e6e6ff;
  font-size: 18px;
  position: absolute;
  right: 0;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const BoardTitleLabel = styled.div`
  height: 7%;
  width: 100px;
  background-color: #c40505;
  margin-bottom: 0.7%;
`;

const BoardTitleText = styled.div`
  height: 80%;
  width: 100%;
  font-size: 32px;
  letter-spacing: 20px;
`;

const BoardBottomSect = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const BoardTitleSect = styled.article`
  width: 100%;
  height: 10vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const BoardTitleBox = styled.div`
  width: 100%;
  height: 100%;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const WritedTitleBox = styled.div`
  width: 100%;
  height: 70%;
  background-color: yellow;
  display: flex;
  align-items: center;
  border-bottom: 1px solid black;
  border-top: 3px solid red;
`;

const LeftTitleSect = styled.div`
  width: 10%;
  height: 100%;
  background-color: white;
  display: flex;
  align-items: center;
  padding-left: 50px;
  font-size: 16px;
`;

const CenterTitleBox = styled.div`
  width: 70%;
  height: 100%;
  display: flex;
  align-items: center;
  font-size: 22px;
  background-color: white;
  padding-left: 30px;
`;

const RightInfoBox = styled.div`
  width: 20%;
  height: 100%;
  background-color: white;
  align-items: center;
  justify-content: center;
  display: flex;
  font-size: 16px;
`;

const WritedInfoBox = styled.div`
  width: 100%;
  height: 30%;
  background-color: white;
  display: flex;
`;

const LeftWirterBox = styled.div`
  width: 10%;
  height: 100%;
  background-color: white;
  display: flex;
  align-items: center;
  padding-left: 50px;
  font-size: 16px;
`;

const RightWirterInfoBox = styled.div`
  width: 50%;
  background-color: white;
  padding-left: 30px;
  font-size: 16px;
  display: flex;
  align-items: center;
`;

const OneBoardFooterSect = styled.div`
  width: 100%;
  height: 15vh;
  background-color: #fffff0;
`;

const BasicSizeContentBox = styled.section`
  width: 100%;
  border-bottom: 2px solid black;
  font-size: 16px;
  padding-left: 40px;
`;

const COneContTextBox = styled.article`
  width: 100%;
  margin-top: 30px;
  margin-bottom: 50px;
  line-height: 1.5;
  font-size: 16px;
`;

const BoardOneCommentSect = styled.article`
  width: 100%;
  margin-top: 5%;
  display: flex;
  flex-direction: column;
  border-bottom: 3px solid brown;
  border-top: 3px solid brown;
`;

const BoardOneCommentOne = styled.div`
  width: 100%;
  height: 4vh;
  display: flex;
  align-items: center;
  font-size: 16px;
  border-bottom: 1px solid gray;
`;

const CommentOneLeft = styled.div`
  width: 15%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  border-right: 1px solid black;
`;

const CommentOneCenter = styled.div`
  width: 70%;
  height: 100%;
  display: flex;
  align-items: center;
  padding-left: 30px;
  font-size: 16px;
  border-right: 1px solid black;
`;

const CommentOneRight = styled.div`
  width: 15%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const CommentWriteBoxSect = styled.article`
  width: 100%;
  height: 20vh;
  margin-top: 1%;
  border-top: 2px solid gray;
  border-bottom: 2px solid gray;
  display: flex;
  flex-direction: column;
`;

const CommentUpSect = styled.div`
  background-color: white;
  height: 70%;
  width: 100%;
  display: flex;
`;

const CommentBottomSect = styled.div`
  width: 100%;
  height: 30%;
  margin-bottom: 2px;
  margin-top: 2px;
  display: flex;
  justify-content: end;
  align-items: center;
`;

const CommentBottomBtn = styled.div`
  width: 7%;
  height: 65%;
  cursor: pointer;
  background-color: #7fceff;
  border: 1px solid black;
  margin-right: 4.2%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1px;
  border-radius: 10px;
  font-size: 16px;
`;

const CommentUpLeft = styled.div`
  width: 15%;
  height: 100%;
  align-items: center;
  display: flex;
  flex-direction: column;
`;

const CommentUserBox = styled.div`
  width: 150px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid gray;
  border-radius: 5px;
  font-size: 16px;
  margin-top: 15px;
`;

// const CommentPassBox = styled.div`
//   width: 75%;
//   height: 30%;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   border: 1px solid gray;
//   border-radius: 5px;
// `;

const CommentUpRight = styled.div`
  width: 85%;
  height: 100%;
  margin-top: 1%;
`;

const CommentRightInputBox = styled.div`
  width: 95%;
  height: 90%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  border: 1px solid gray;
`;

export const ClubOneContPage = () => {
  // const navigate = useNavigate();
  const { clubBoardId } = useParams();
  const { category } = useParams();
  const [oneClubData, setOneClubData] = useState([]);
  const OneContApiUrl = `https://9dac-2406-5900-705c-f80b-2c90-ee5-6e07-7434.ngrok-free.app/clubBoards/${category}/${clubBoardId}`;

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get(OneContApiUrl, {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        });

        if (response.status === 200) {
          setOneClubData(response.data);
          console.log(response.data);
          console.log(oneClubData);
        } else {
          console.error("데이터 가져오기 실패");
        }
      } catch (error) {
        console.error("데이터 가져오기 실패", error);
      }
    }
    fetchData();
  }, []);
  return (
    <>
      <NewHeader />
      <BoardOneContContainer>
        <BoardOneFilterSect></BoardOneFilterSect>
        <BoardOneContentSect>
          <BoardUpSect>
            <BoardTitleText>
              <BoardTitleLabel />
              만화
            </BoardTitleText>
            <PrevBoardBtn>글 목록 가기</PrevBoardBtn>
          </BoardUpSect>
          <BoardBottomSect>
            <BoardTitleSect>
              <BoardTitleBox>
                <WritedTitleBox>
                  <LeftTitleSect>제목</LeftTitleSect>
                  <CenterTitleBox>{oneClubData.title}</CenterTitleBox>
                  <RightInfoBox>
                    {oneClubData.createdAt} | 조회수 {oneClubData.viewCount}
                  </RightInfoBox>
                </WritedTitleBox>
                <WritedInfoBox>
                  <LeftWirterBox>작성자</LeftWirterBox>
                  <RightWirterInfoBox>
                    {oneClubData.nickname}
                  </RightWirterInfoBox>
                </WritedInfoBox>
              </BoardTitleBox>
            </BoardTitleSect>

            <BasicSizeContentBox>
              <COneContTextBox>
                {oneClubData.content}
                <br />
                <br />
                <br />
                <br />
                <br />
                {oneClubData.content}
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                <br />
                {oneClubData.content}
                <br />
              </COneContTextBox>
            </BasicSizeContentBox>

            <BoardOneCommentSect>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
            </BoardOneCommentSect>

            <CommentWriteBoxSect>
              <CommentUpSect>
                <CommentUpLeft>
                  <CommentUserBox>작성자</CommentUserBox>
                </CommentUpLeft>
                <CommentUpRight>
                  <CommentRightInputBox />
                </CommentUpRight>
              </CommentUpSect>

              <CommentBottomSect>
                <CommentBottomBtn>등록</CommentBottomBtn>
              </CommentBottomSect>
            </CommentWriteBoxSect>
          </BoardBottomSect>
        </BoardOneContentSect>
      </BoardOneContContainer>
      <OneBoardFooterSect />
    </>
  );
};
