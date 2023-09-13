import { styled } from "styled-components";
import React from "react";

const BoardOneSect = styled.section`
  width: 100%;
  height: 450px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const BoardOneTitle = styled.div`
  width: 75%;
  font-size: 22px;
  font-weight: bold;
  margin-top: 2%;
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
  width: 100px;
  height: 50px;
  font-size: 22px;
  position: relative;
  text-decoration: underline;
  text-underline-offset: 10px;
`;

const BoardNoteSect = styled.div`
  width: 75%;
  height: 400px;
  display: flex;
  justify-content: space-between;
`;

const BoardNote = styled.div`
  width: 31.5%;
  height: 70%;
  border-radius: 10px;
  background-color: ${(props) =>
    props.color === "red" ? "#FFC0CB" : "#ADD8E6"};
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
  overflow: hidden;
  position: relative;
  margin-bottom: 1%;
  &:hover {
    border: 10px solid #9bfff5;
  }
`;

const BoardNoteTitle = styled.h3`
  font-size: 18px;
  width: 100%;
  height: 20%;
  color: black;
  display: flex;
  align-items: flex-end;
  justify-content: center;
`;

const BoardNoteCircle = styled.div`
  border-radius: 50%;
  background-color: white;
  width: 110%;
  height: 100%;
  top: 30%;
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
export const HotContent = ({ title, color }) => {
  return (
    <>
      <BoardOneSect>
        <BoardOneTitle>
          <BoardTitleDiv>{title}</BoardTitleDiv>
        </BoardOneTitle>
        <BoardNoteSect>
          <BoardNote color={color}>
            <BoardNoteTitle>디지몬 어드벤처 비디오 팔아요</BoardNoteTitle>

            <BoardNoteCircle>
              <BoardCircleImg />
            </BoardNoteCircle>
          </BoardNote>

          <BoardNote color={color}>
            <BoardNoteTitle>포켓몬 스티커 팔아요</BoardNoteTitle>

            <BoardNoteCircle>
              <BoardCircleImg style={{ objectPosition: "bottom center" }} />
            </BoardNoteCircle>
          </BoardNote>

          <BoardNote color={color}>
            <BoardNoteTitle>원피스 피규어 팔아요</BoardNoteTitle>

            <BoardNoteCircle>
              <BoardCircleImg />
            </BoardNoteCircle>
          </BoardNote>
        </BoardNoteSect>
      </BoardOneSect>
    </>
  );
};
