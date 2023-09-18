import { styled } from "styled-components";
import { NewHeader } from "../components/NewHeader.jsx";
import { HotContent } from "../components/HotContent.jsx";
import { NormalContent } from "../components/NormalContent.jsx";
import { AnaLogClock } from "../components/Clock.jsx";
import React, { useState, useEffect } from "react";
import axios from "axios";

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
  background-color: #083d03;
  margin-top: 0.2%;
  box-sizing: border-box;
  border-top: 20px solid brown;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const BoardFilterBtnSect = styled.div`
  width: 100%;
  height: 320px;
  margin-top: 3.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  background-color: #d7d7fe;
`;

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
  width: 6vw;
  height: 30vh;
  position: absolute;
  top: 5%;
  left: 3%;
  display: flex;
  flex-direction: column;
`;

const LetterTitleSect = styled.div`
  width: 100%;
  height: 30%;
  border-radius: 5px;
  font-size: 22px;
  background-color: #ffe9e9;
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
  height: 30%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  border: 0.5px solid black;
  background-color: white;
  margin-top: 7%;
  border-radius: 5px;
  font-size: 18px;
`;

const MarketMoreReadBtn = styled.div`
  width: 150px;
  height: 50px;
  border-radius: 30px;
  background-color: #083d03;
  border: 3px solid white;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  margin-top: 30px;
  margin-bottom: 50px;
`;

const BoardFooterSect = styled.div`
  width: 100%;
  height: 10vh;
  background-color: #fffff0;
`;

export const MarketTContPage = () => {
  const [MarketTData, setMarketTData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "https://49c9-221-150-55-48.ngrok-free.app/marketBoards",
          {
            headers: {
              "Content-Type": `application/json`,
              "ngrok-skip-browser-warning": "69420",
            },
          },
        );
        console.log(response);
        setMarketTData(response.data);
      } catch (error) {
        console.error("Error fetching the data", error);
      }
    };

    fetchData();
  }, []);
  console.log(MarketTData);
  const HotContentData = MarketTData.slice(0, 3);
  const NormalContentData = MarketTData.slice(0, 4);

  return (
    <TotalContainer>
      <NewHeader />

      <BoardFilterBtnSect>
        <ClassTeachingBoard>
          <TeachTitle>급훈</TeachTitle>
          <TeachBoardFilterSect>추억은 일종의 만남이다.</TeachBoardFilterSect>
          <AnaLogClock />
        </ClassTeachingBoard>
      </BoardFilterBtnSect>

      <BoardNoteContainer>
        <BoardNoteSection>
          <BoardLetterSect>
            <LetterTitleSect>카테고리</LetterTitleSect>
            <LetterContUl>
              <LetterContLi>판매중인 물건</LetterContLi>
              <LetterContLi>판매된 물건</LetterContLi>
            </LetterContUl>
          </BoardLetterSect>

          <HotContent
            title="최신 판매"
            color="red"
            HotContentData={HotContentData}
          />
          <HotContent
            title="오래된 판매"
            color="blue"
            HotContentData={HotContentData}
          />
          <NormalContent NormalContentData={NormalContentData} />
          <NormalContent NormalContentData={NormalContentData} />
          <NormalContent NormalContentData={NormalContentData} />
          <MarketMoreReadBtn>더 보기</MarketMoreReadBtn>
        </BoardNoteSection>
      </BoardNoteContainer>
      <BoardFooterSect />
    </TotalContainer>
  );
};
