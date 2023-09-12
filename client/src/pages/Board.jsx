import { styled } from "styled-components";
import { BoardDial } from "../components/BoardDial.jsx";
import { useState } from "react";
import { NewHeader } from "../components/NewHeader.jsx";
import boardmarker from "../images/theme/white board marker.jpg";

const BoardTotalContainer = styled.section`
  width: 100vw;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-items: center;
`;

const BoardNameSect = styled.section`
  margin-top: 8%;
  width: 75%;
  height: 45vh;
  display: flex;
  flex-direction: column;
`;

const TitleSect = styled.div`
  width: 20%;
  height: 15%;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
`;

const TitleLabel = styled.div`
  height: 7%;
  width: 45%;
  background-color: #c40505;
  margin-bottom: 0.5%;
`;

const TitleText = styled.div`
  height: 90%;
  width: 100%;
  font-size: 32px;
  letter-spacing: 20px;
`;

const FilterBtnContainer = styled.div`
  width: 100%;
  margin-top: 2%;
  background-color: #86c65b;
  border: 2px solid black;
  height: 15%;
  font-size: 18px;
  display: flex;
  align-items: center;
  overflow: hidden;
  border-radius: 20px;
`;

const HomeBtnContainer = styled.div`
  width: 5.5%;
  height: 100%;
`;

const FilterContentUl = styled.ul`
  width: 60%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const FilterContentLi = styled.li`
  width: 33%;
  height: 100%;
  border-right: 0.5px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  cursor: pointer;
  background-color: white;
`;

const FilterContentLi2 = styled.li`
  width: 33%;
  height: 100%;
  border-right: 0.5px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  cursor: pointer;
`;

const HotBoard = styled.div`
  width: 100%;
  height: 40%;
  border-radius: 10px;
  border: 8px solid gray;
  position: relative;
  margin-top: 3%;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-items: center;
  justify-content: center;
`;

const HotBoardHelp = styled.div`
  width: 20%;
  height: 30%;
  text-align: center;
  font-size: 22px;
`;

const HotBtnSect = styled.div`
  width: 80%;
  height: 40%;
  display: flex;
  margin-top: 1%;
  justify-content: center;
  align-items: center;
`;

const HotBtn = styled.div`
  cursor: pointer;
  width: 12%;
  margin-left: 1%;
  height: 90%;
  border-radius: 15px;
  background-color: white;
  border: 1px solid blue;
  color: #718be8;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  &:nth-child(0) {
    margin-left: 0;
  }
`;

const BoardContentCont = styled.section`
  width: 75%;
  height: auto;
  display: flex;
  justify-content: center;
  background-color: white;
`;

const BoardMarkerImg = styled.img`
  width: 7%;
  height: 40%;
  position: absolute;
  bottom: 5%;
  right: 6%;
`;

export const Board = () => {
  const [contentSelectTab, setContentSelectTab] = useState(0);
  const filtercontent = ["이야기", "물건 감정", "공유"];
  const filtercontentBtnClick = (idx) => {
    setContentSelectTab(idx);
  };

  return (
    <>
      <BoardTotalContainer>
        <NewHeader />

        <BoardNameSect>
          <TitleSect>
            <TitleLabel />
            <TitleText>만화</TitleText>
          </TitleSect>

          <FilterBtnContainer>
            <HomeBtnContainer></HomeBtnContainer>
            <FilterContentUl>
              {filtercontent.map((item, idx) => {
                if (idx === contentSelectTab) {
                  return (
                    <FilterContentLi
                      onClick={() => filtercontentBtnClick(idx)}
                      key={idx}
                    >
                      {item}
                    </FilterContentLi>
                  );
                } else {
                  return (
                    <FilterContentLi2
                      onClick={() => filtercontentBtnClick(idx)}
                      key={idx}
                    >
                      {item}
                    </FilterContentLi2>
                  );
                }
              })}
            </FilterContentUl>
          </FilterBtnContainer>

          <HotBoard>
            <HotBoardHelp>핫 게시물 바로가기</HotBoardHelp>

            <HotBtnSect>
              <HotBtn>디지몬</HotBtn>
              <HotBtn>포켓몬</HotBtn>
              <HotBtn>탑블레이드</HotBtn>
              <HotBtn>야인시대</HotBtn>
              <HotBtn>딱지</HotBtn>
              <HotBtn>학종이</HotBtn>
              <HotBtn>판치기</HotBtn>
            </HotBtnSect>

            <BoardMarkerImg src={boardmarker} />
          </HotBoard>
        </BoardNameSect>

        <BoardContentCont>
          {contentSelectTab === 0 ? <BoardDial /> : null}
        </BoardContentCont>
      </BoardTotalContainer>
    </>
  );
};
