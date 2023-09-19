import { styled } from "styled-components";
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
  margin-top: 72px;
`;

const BoardNoteContainer = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  background-color: #438b7b;
  box-sizing: border-box;
  border-top: 20px solid brown;
  justify-content: center;
  align-items: center;
  position: relative;
`;

const BoardFilterBtnSect = styled.div`
  width: 100%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  background-color: #ebebff;
  z-index: 1005;
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
  position: fixed;
  z-index: 995;
  top: 12%;
  left: 3%;
  display: flex;
  flex-direction: column;
  opacity: ${(props) => (props.isVisible ? "1" : "0")};
  transition: opacity 0.3s ease-in-out;
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
  display: ${(props) => (!props.noMoreRead ? "flex" : "none")};
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

// const NormalContentTitleSect = styled.div`
//   width: 100%;
//   height: 100px;
//   font-size: 22px;
//   margin-top: 50px;
// `;

export const MarketTContPage = () => {
  const [MarketTData, setMarketTData] = useState([]);
  // const [HotContentData, setHotContentData] = useState([]);
  const [NormalContentData, setNormalContentData] = useState([]);
  const [menuIsVisible, setMenuIsVisible] = useState(false);
  const [noMoreRead, setNoMoreRead] = useState(false);
  const authorizationHeaderValue = useSelector(
    (state) => state.user.authorization,
  );
  console.log(authorizationHeaderValue);

  useEffect(() => {
    const hanldeMenuScroll = () => {
      if (window.scrollY >= 300) {
        setMenuIsVisible(true);
      } else {
        setMenuIsVisible(false);
      }
    };

    window.addEventListener("scroll", hanldeMenuScroll);

    return () => {
      window.removeEventListener("scroll", hanldeMenuScroll);
    };
  }, []);

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

        setMarketTData(response.data);
        console.log(MarketTData);
      } catch (error) {
        console.error("Error fetching the data", error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    if (MarketTData.length > 0) {
      // const hotData = MarketTData.slice(0, 4);
      const normalData = MarketTData.slice(0, 4);
      // setHotContentData(hotData);
      setNormalContentData(normalData);
    }
  }, [MarketTData]);
  useEffect(() => {
    if (NormalContentData.length >= MarketTData.length) {
      setNoMoreRead(true);
    }
  }, [NormalContentData]);

  const MoreReadNormalData = () => {
    const startIndex = NormalContent.length;
    const endIndex = startIndex + 8;
    const moreData = MarketTData.slice(startIndex, endIndex);
    setNormalContentData((prev) => [...prev, ...moreData]);
  };

  console.log(MarketTData);

  return (
    <TotalContainer>
      <BoardFilterBtnSect>
        <ClassTeachingBoard>
          <TeachTitle>급훈</TeachTitle>
          <TeachBoardFilterSect>추억은 일종의 만남이다.</TeachBoardFilterSect>
          <AnaLogClock />
        </ClassTeachingBoard>
      </BoardFilterBtnSect>

      <BoardNoteContainer>
        <BoardNoteSection>
          <BoardLetterSect isVisible={menuIsVisible}>
            <LetterTitleSect>카테고리</LetterTitleSect>
            <LetterContUl>
              <LetterContLi>판매중인 물건</LetterContLi>
              <LetterContLi>판매된 물건</LetterContLi>
            </LetterContUl>
          </BoardLetterSect>

          <HotContent
            title="판매중인 물건"
            HotContentData={NormalContentData}
          />
          {/* <NormalContentTitleSect>판매중인 물건 </NormalContentTitleSect>
          <NormalContent NormalContentData={NormalContentData} /> */}
          <MarketMoreReadBtn
            onClick={MoreReadNormalData}
            noMoreRead={noMoreRead}
          >
            더 보기
          </MarketMoreReadBtn>
        </BoardNoteSection>
      </BoardNoteContainer>
      <BoardFooterSect />
    </TotalContainer>
  );
};
