import { styled } from "styled-components";
import { NewHeader } from "../components/NewHeader.jsx";
import { HotContent } from "../components/HotContent.jsx";
import { NormalContent } from "../components/NormalContent.jsx";
import { AnaLogClock } from "../components/Clock.jsx";

const TotalContainer = styled.div`
  width: 100vw;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
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
  justify-content: center;
  align-items: center;
  position: relative;
`;

const BoardFilterBtnSect = styled.div`
  width: 100%;
  height: 28vh;
  margin-top: 3.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  background-color: #d7d7fe;
`;

// const BoardFilterUl = styled.ul`
//   width: 99%;
//   height: 100%;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   margin-top: 3%;
// `;

// const BoardFilterLi = styled.li`
//   width: 13%;
//   margin-left: 3.7%;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   font-size: 22px;
//   cursor:pointer;
//   border-bottom: 10px solid black;
//   border-top: 10px solid black;
//   background-color: #ba8862;
//   height: 100%;
//   color: white;
//   letter-spacing: 3px;
//   border-radius: 10px;
// `;

const ClassTeachingBoard = styled.div`
  width: 23%;
  background-color: white;
  height: 90%;
  border: 10px solid #b4714b;
  display: flex;
  flex-direction: column;
`;

const TeachTitle = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  height: 30%;
`;

const TeachBoardFilterSect = styled.div`
  width: 100%;
  height: 60%;
  margin-top: 10%;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 32px;
`;

const BoardNoteSection = styled.div`
  width: 75%;
  height: auto;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
`;

const BoardLetterSect = styled.div`
  width: 4vw;
  height: 30vh;
  position: absolute;
  top: 5%;
  left: 4%;
  display: flex;
  flex-direction: column;
`;

const LetterTitleSect = styled.div`
  width: 100%;
  height: 15%;
  border-radius: 5px;
  background-color: #ffe9e9;
  font-size: 13px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const LetterContUl = styled.ul`
  height: 80%;
  width: 100%;
  font-size: 13px;
  flex-direction: column;
`;

const LetterContLi = styled.li`
  height: 15%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border: 0.5px solid black;
  background-color: white;
  margin-top: 5%;
  border-radius: 5px;
`;

export const MarketTContPage = () => {
  return (
    <TotalContainer>
      <NewHeader />

      <BoardFilterBtnSect>
        <ClassTeachingBoard>
          <TeachTitle>우리반 카테고리</TeachTitle>
          <TeachBoardFilterSect>만화영화</TeachBoardFilterSect>
          <AnaLogClock />
        </ClassTeachingBoard>
      </BoardFilterBtnSect>

      <BoardNoteContainer>
        <BoardNoteSection>
          <BoardLetterSect>
            <LetterTitleSect>오늘 시간표</LetterTitleSect>
            <LetterContUl>
              <LetterContLi>만화영화</LetterContLi>
              <LetterContLi>만화영화</LetterContLi>
              <LetterContLi>만화영화</LetterContLi>
              <LetterContLi>만화영화</LetterContLi>
              <LetterContLi>만화영화</LetterContLi>
              <LetterContLi>만화영화</LetterContLi>
            </LetterContUl>
          </BoardLetterSect>

          <HotContent />
          <HotContent />
          <NormalContent />
          <NormalContent />
        </BoardNoteSection>
      </BoardNoteContainer>
    </TotalContainer>
  );
};
