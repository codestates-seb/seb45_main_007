import { styled } from "styled-components";
import React from "react";
import pinImg from "../images/pin.png";
import chalkImg from "../images/chalk.png";
import { Link } from "react-router-dom";

// const BoardOneSect = styled.section`
//   width: 1440px;
//   height: 550px;
//   display: flex;
//   justify-content: center;
//   flex-direction: column;
//   align-items: center;
//   margin-bottom: 30px;
// `;
const BoardOneSect = styled.section`
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  flex-wrap: wrap;
  height: auto;
`;

const BoardNoteSect = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-wrap: wrap;
`;
// const BoardNoteSect = styled.div`
//   width: 100%;
//   height: 500px;
//   display: flex;
//   justify-content: space-around;
//   flex-wrap: wrap;
// `;
const BoardOneTitle = styled.div`
  width: 100%;
  font-size: 22px;
  font-weight: bold;
  margin-top: 100px;
  position: relative;
  color: white;
  font-family: "HakgyoansimBunpilR";
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  .more {
    &:hover {
      cursor: pointer;
      text-decoration: underline;
    }
  }
`;

const BoardTitleDiv = styled.div`
  width: 100%;
  height: 50px;
  font-size: 24px;
  position: relative;
  text-decoration: underline;
  text-underline-offset: 10px;
`;

const BoardTitleChalkImg = styled.img`
  width: 30px;
  height: 30px;
  left: -40px;
  position: absolute;
`;

const BoardNote = styled(Link)`
  width: calc(24% - 20px);
  height: 75%;
  border-radius: 10px;
  margin-top: 1%;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-wrap: wrap;
  align-items: center;
  font-size: 50px;
  text-align: center;
  position: relative;
  background-color: white;
  border: 10px solid white;
`;

const BoardNotePhotoTape = styled.img`
  width: 30px;
  height: 30px;
  position: absolute;
  top: -30px;
  left: 45%;
  z-index: 999;
`;

const BoardNoteInnerLine = styled.div`
  width: 90%;
  height: 90%;
  border-bottom: 1px solid #474747;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  overflow: hidden;
`;

const BoardNoteCircle = styled.div`
  width: 100%;
  height: 90%;
  &:hover {
    height: 120%;
    transition: 0.2s ease-in;
  }
  img {
    width: 100%;
    height: 250px;
  }
`;

const BoardCircleImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
`;

const BoardNoteTitle = styled.div`
  width: 100%;
  height: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
`;

// eslint-disable-next-line react/prop-types
export const HotContent = ({ title, HotContentData }) => {
  return (
    <>
      <BoardOneSect>
        <BoardOneTitle>
          <BoardTitleDiv>{title}</BoardTitleDiv>
          <BoardTitleChalkImg src={chalkImg} />
        </BoardOneTitle>
        <BoardNoteSect>
          {/* eslint-disable-next-line react/prop-types */}
          {HotContentData.map((data) => (
            <BoardNote
              to={`/market/${data.marketBoardId}`}
              key={data.market_board_id}
            >
              <BoardNotePhotoTape src={pinImg} />
              <BoardNoteInnerLine>
                <BoardNoteCircle>
                  <BoardCircleImg src={data.photo} />
                </BoardNoteCircle>
                <BoardNoteTitle>{data.title}</BoardNoteTitle>
              </BoardNoteInnerLine>
            </BoardNote>
          ))}
        </BoardNoteSect>
      </BoardOneSect>
    </>
  );
};
