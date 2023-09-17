import { styled } from "styled-components";
import arrowIcon from "../icon/arrow-right.png";
import nextIcon from "../icon/next.png";
import preIcon from "../icon/pre.png";
import { HotContent } from "../components/HotContent.jsx";
import React from "react";
import { NewHeader } from "../components/NewHeader.jsx";
import charImg from "../images/userExample.png";
// import itemImg from "../images/chi021.png";

export default function MarketOneContPage() {
  return (
    <>
      <NewHeader />
      <TotalContainer>
        <BoardNoteContainer>
          <ContentContainer>
            <Margnet />
            <ContentTitle>센과 치히로 비디오 팝니다</ContentTitle>
            <SecondContainer>
              {/* <CreatedAt>2023.09.10</CreatedAt> */}
              <WriterContainer>
                작성자
                <WriterImage>
                  <img src={charImg} alt="user" />
                </WriterImage>
              </WriterContainer>
            </SecondContainer>
            <ContentImage>
              <Icon className="pre">
                <img src={preIcon} alt="previous" />
              </Icon>
              <img src="" alt="mdExample" style={{ height: "650px" }} />
              <Icon className="next">
                <img src={nextIcon} alt="next" />
              </Icon>
            </ContentImage>
            <TextContentContainer>
              <Price>30000원</Price>
              <TextContent>
                30000원부터 시작합니다 <br />
                팝니다
                <br />팜<br />
                팔아요
                <br />
                사실분
                <br />
                유지웅
                <br />
                최승연
              </TextContent>
            </TextContentContainer>
            <BottomContainer>
              <div>조회수 : 300</div> <div>찜 : 20</div> <div>댓글 : 3</div>
            </BottomContainer>
          </ContentContainer>
          <ReplyContainer>
            <ReplyIcon>
              <img src={arrowIcon} alt="icon" />
            </ReplyIcon>
            <ReplyWriterConatiner>작성자 : </ReplyWriterConatiner>
            <ReplyContents>삽니다</ReplyContents>
            <ReplyCreatedAt>2023.08.23</ReplyCreatedAt>
          </ReplyContainer>
          <ReplyContainer>
            <ReplyIcon>
              <img src={arrowIcon} alt="icon" />
            </ReplyIcon>
            <ReplyWriterConatiner>작성자 : </ReplyWriterConatiner>
            <ReplyContents>
              가<br />나<br />다<br />라<br />마<br />바
            </ReplyContents>
            <ReplyCreatedAt>2023.08.23</ReplyCreatedAt>
          </ReplyContainer>
          <ReplyContainer>
            <ReplyIcon>
              <img src={arrowIcon} alt="icon" />
            </ReplyIcon>
            <ReplyWriterConatiner>작성자 : </ReplyWriterConatiner>
            <ReplyContents>마이크테스트</ReplyContents>
            <ReplyCreatedAt>2023.08.23</ReplyCreatedAt>
          </ReplyContainer>
          <ReplyInput type="text" placeholder="댓글입력"></ReplyInput>
          <ButtonContainer>
            <Button>확인</Button>
          </ButtonContainer>
          <HotContent />
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
const Icon = styled.div`
  width: 30px;
  height: 30px;
  display: flex;
  justify-content: center;
  &:hover {
    cursor: pointer;
    filter: opacity(0.5) drop-shadow(0 0 0 gray);
  }
`;

const WriterContainer = styled.div`
  display: flex;
  width: 100%;
  height: 25px;
  align-items: center;
  justify-content: end;
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
// const CreatedAt = styled.div`
//   width: 100%;
//   color: #756e6e;
//   display: flex;
//   align-items: center;
// `;
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
  div {
    padding-right: 20px;
    margin-right: 15px;
  }
`;

const ReplyContainer = styled.div`
  width: 75%;
  color: white;
  font-family: "HakgyoansimBunpilR";
  font-size: 30px;
  font-weight: bold;
  border-bottom: 2px solid white;
  display: flex;
  flex-direction: row;
  margin-top: 30px;
`;
const ReplyWriterConatiner = styled.div`
  display: flex;
  margin-left: 20px;
  width: 7%;
  font-size: 20px;
  padding-top: 20px;
`;

const ReplyContents = styled.div`
  width: 100%;
  margin: 20px 0;
  font-size: 20px;
`;
const ReplyCreatedAt = styled.div`
  font-family: "HakgyoansimBunpilR";
  color: white;
  font-size: 16px;
  display: flex;
  align-items: end;
  padding-bottom: 20px;
`;
const ReplyIcon = styled.div`
  img {
    width: 45px;
    height: 45px;
  }
`;
const ReplyInput = styled.textarea`
  width: 75%;
  height: 200px;
  background: none;
  font-size: 26px;
  color: white;
  font-family: "HakgyoansimBunpilR";
  border: 2px solid white;
  margin-top: 30px;
  overflow-y: auto;
  border-radius: 8px;

  &:focus {
    outline: none;
  }
`;
const Button = styled.button`
  display: flex;
  width: 155px;
  height: 48px;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  background: none;
  color: #fff;
  font-size: 20px;
  border: 2px solid white;
  font-family: "HakgyoansimBunpilR";
  font-weight: bold;
  &:hover {
    cursor: pointer;
  }
`;
const ButtonContainer = styled.div`
  display: flex;
  width: 75%;
  justify-content: end;
  margin: 30px 0;
`;
