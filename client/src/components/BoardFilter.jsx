import { NavLink } from "react-router-dom";
import { styled } from "styled-components";
import React from "react";
import movieIcon from "../images/filterBar/movieIcon.png";
import comicIcon from "../images/filterBar/comicIcon.png";
import tvIcon from "../images/filterBar/tvIcon.png";
import itemIcon from "../images/filterBar/itemIcon.png";
import musicIcon from "../images/filterBar/musicIcon.png";
import gameIcon from "../images/filterBar/gameIcon.png";

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
  font-size: 20px;
  display: flex;
`;

const ThreeBoxContent = styled.div`
  width: 100%;
  height: 15%;
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

const ThreeBoxItemImg = styled.img`
  width: 15px;
  height: 15px;
  margin-right: 10px;
`;

const ThreeBoxCircle = styled.div`
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin-left: 5px;
  background-color: ${(props) => props.color};
`;

export const BoardFilter = () => {
  const ThreeBoxContentArr = [
    {
      NavLink: "/club/comic",
      itemName: "만화",
      menuColor: "#800000",
      imgsrc: comicIcon,
    },
    {
      NavLink: "/club/movie",
      itemName: "영화",
      menuColor: "#ff4500",
      imgsrc: movieIcon,
    },
    {
      NavLink: "/club/tvshow",
      itemName: "TV 프로그램",
      menuColor: "#cccc00",
      imgsrc: tvIcon,
    },
    {
      NavLink: "/club/item",
      itemName: "추억 아이템",
      menuColor: "#006400",
      imgsrc: itemIcon,
    },
    {
      NavLink: "/club/music",
      itemName: "노래",
      menuColor: "#00008b",
      imgsrc: musicIcon,
    },
    {
      NavLink: "/club/game",
      itemName: "게임",
      menuColor: "#4b0082",
      imgsrc: gameIcon,
    },
  ];

  return (
    <>
      <BoardFilterSect>
        <BoardFilterBox>
          <BoardFilterThreeBox>
            <ThreeBoxTitle>게시판 이동하기</ThreeBoxTitle>

            {ThreeBoxContentArr.map((item) => (
              <ThreeBoxContent key={item.itemName}>
                <ThreeBoxItemImg src={item.imgsrc} />
                <NavLink to={item.NavLink}>{item.itemName}</NavLink>
                <ThreeBoxCircle color={item.menuColor} />
              </ThreeBoxContent>
            ))}
          </BoardFilterThreeBox>
        </BoardFilterBox>
      </BoardFilterSect>
    </>
  );
};
