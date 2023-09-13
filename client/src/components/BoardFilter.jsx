import { NavLink } from "react-router-dom";
import { styled } from "styled-components";
import React from "react";

const BoardFilterSect = styled.section`
  width: 12.5%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  position: relative;
`;

const BoardFilterBox = styled.div`
  width: 9%;
  height: 40vh;
  border: 0.5px solid #718be8;
  border-radius: 10px;
  position: fixed;
  display: flex;
  flex-direction: column;
  top: 30%;
  left: 1%;
`;

const BoardFilterThreeBox = styled.div`
  width: 100%;
  height: 90%;
  display: flex;
  flex-direction: column;
`;

const ThreeBoxTitle = styled.div`
  width: 100%;
  height: 20%;
  border-bottom: 1px solid green;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  display: flex;
`;

const ThreeBoxContent = styled.div`
  width: 100%;
  height: 13%;
  display: flex;
  padding: 10px;
  padding-left: 18px;
  align-items: center;
  font-size: 18px;
  border-bottom: 0.001px solid black;
  background-color: #ededed;
  cursor: pointer;
  text-align: center;
`;

const ThreeBoxCircle = styled.div`
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin-left: 5px;
  background-color: ${(props) => props.color};
`;

export const BoardFilter = () => {
  return (
    <>
      <BoardFilterSect>
        <BoardFilterBox>
          <BoardFilterThreeBox>
            <ThreeBoxTitle>게시판 이동하기</ThreeBoxTitle>

            <ThreeBoxContent>
              <NavLink to="/club/totalcontents">만화</NavLink>
              <ThreeBoxCircle color="red" />
            </ThreeBoxContent>

            <ThreeBoxContent>
              영화
              <ThreeBoxCircle color="blue" />
            </ThreeBoxContent>
            <ThreeBoxContent>
              TV 프로그램
              <ThreeBoxCircle color="green" />
            </ThreeBoxContent>
            <ThreeBoxContent>
              추억 아이템
              <ThreeBoxCircle color="yellow" />
            </ThreeBoxContent>
            <ThreeBoxContent>
              노래
              <ThreeBoxCircle color="purple" />
            </ThreeBoxContent>
            <ThreeBoxContent>
              게임
              <ThreeBoxCircle color="white" />
            </ThreeBoxContent>
          </BoardFilterThreeBox>
        </BoardFilterBox>
      </BoardFilterSect>
    </>
  );
};
