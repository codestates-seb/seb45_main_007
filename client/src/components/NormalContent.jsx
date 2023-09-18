import { styled } from "styled-components";
import React from "react";

const BoardOneSect = styled.section`
  width: 100%;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  flex-wrap: wrap;
`;

const BoardNoteSect = styled.div`
  width: 100%;

  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
`;

const OneBoard = styled.article`
  width: 22%;
  height: 500px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  position: relative;
  margin-right: 30px;
`;

const BoardNote = styled.div`
  width: 100%;
  height: 75%;
  border-radius: 10px;
  background-color: white;
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
    border: 30px solid white;
    transition: 0.1s ease-in;
  }
`;

const BoardNoteTitle = styled.h3`
  font-size: 18px;
  width: 100%;
  height: 50px;
  color: black;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  margin-top: 10px;
`;

const BoardNoteCircle = styled.div`
  width: 100%;
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
  width: 100%;
  height: 100%;
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

            <OneBoard key={data.marketBoardId}>
              <BoardTitleCircle />
              <BoardNote>
                <BoardNoteCircle>
                  <BoardCircleImg
                    src={data.baseImg}
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
