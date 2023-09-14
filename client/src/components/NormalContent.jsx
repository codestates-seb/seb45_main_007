import { styled } from "styled-components";
import chulsuImg from "../images/theme/chulsu.jpg";
import React from "react";

const BoardOneSect = styled.section`
  width: 100%;
  height: 350px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

// const BoardOneTitle = styled.div`
//   width: 18%;
//   height: 10%;
//   border-bottom: 3px solid #a7cfff;
//   font-size: 22px;
//   margin-top: 2%;
//   text-align: center;
//   color: white;
// `;

const BoardNoteSect = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const OneBoard = styled.article`
  width: 375px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  position: relative;
`;

const BoardNote = styled.div`
  width: 90%;
  height: 75%;
  border-radius: 10px;
  background-color: #f8f8cd;
  border-bottom: 5px solid black;
  margin-top: 1%;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-items: center;
  color: white;
  font-size: 50px;
  text-align: center;
  margin-left: 30px;
  overflow: hidden;
  position: relative;
  margin-bottom: 1%;

  &:hover {
    border: 10px solid #9bfff5;
    transition: 0.2s ease-in;
  }
`;

const BoardNoteTitle = styled.h3`
  font-size: 18px;
  width: 300px;
  height: 50px;
  color: black;
  display: flex;
  justify-content: center;
  color: white;
  margin-top: 10px;
`;

const BoardTitleCircle = styled.div`
  width: 30px;
  height: 30px;
  position: absolute;
  top: 1.5%;
  z-index: 999;
  left: 6%;
  background-color: #7272f8;
  border-radius: 50%;
`;

const BoardNoteCircle = styled.div`
  border-radius: 50%;
  background-color: #f8f8cd;
  width: 110%;
  height: 100%;
  top: 0%;
  position: absolute;
  z-index: 1001;
  color: black;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  overflow: hidden;
`;

const BoardCircleImg = styled.img`
  width: 70%;
  height: 70%;
  object-fit: cover;
  object-position: top center;
  filter: brightness(0.9);
`;
// eslint-disable-next-line react/prop-types
export const NormalContent = ({ NormalContentData }) => {
  return (
    <>
      <BoardOneSect>
        <BoardNoteSect>
          {/* eslint-disable-next-line react/prop-types */}
          {NormalContentData.map((data) => (
            <OneBoard key={data.market_board_id}>
              <BoardTitleCircle />
              <BoardNote>
                <BoardNoteCircle>
                  <BoardCircleImg
                    src={chulsuImg}
                    style={{ objectPosition: "bottom center" }}
                  />
                </BoardNoteCircle>
              </BoardNote>
              <BoardNoteTitle>{data.title}</BoardNoteTitle>
            </OneBoard>
          ))}
        </BoardNoteSect>
      </BoardOneSect>
    </>
  );
};
