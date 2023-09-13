import { styled } from "styled-components";
import toy1 from "../images/theme/toy1.png";
import React from "react";
import { NavLink } from "react-router-dom";
/* eslint-disable react/prop-types */

const BoardContentSect = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  margin-bottom: 0%;
`;

const BoardOneContent = styled.div`
  height: 8vh;
  width: 100%;
  background-color: white;
  display: flex;
  border-bottom: 1px solid gray;
`;

const OneContentLabelSect = styled.div`
  width: 6%;
  height: 100%;
  background-color: #e3e9ff;
  position: relative;
`;

const OneContLabelImg = styled.img`
  width: 60%;
  position: absolute;
  height: 60%;
  transform: translate(-50%, -50%);
  top: 50%;
  left: 50%;
`;

const OneContentDiv = styled.div`
  width: 90%;
  height: 100%;
  background-color: white;
  font-size: 25px;
  display: flex;
  flex-direction: column;
  cursor: pointer;
`;

const TitleTextSect = styled.div`
  width: 90%;
  height: 50%;
  font-size: 22px;
  display: flex;
  align-items: flex-end;
  margin-left: 2%;
`;

const ContentInfoSect = styled.div`
  width: 90%;
  height: 50%;
  display: flex;
  font-size: 18px;
  align-items: center;
  margin-left: 2%;
`;

export const ClubContents = ({ currentPosts }) => {
  return (
    <>
      <BoardContentSect>
        {currentPosts.map((item, idx) => (
          <BoardOneContent key={idx}>
            <OneContentLabelSect>
              <OneContLabelImg src={toy1} />
            </OneContentLabelSect>

            <OneContentDiv>
              <TitleTextSect>
                <NavLink to="/club/onecontent">{item.title}</NavLink>
              </TitleTextSect>
              <ContentInfoSect>
                작성자 : {item.writer} &nbsp;&nbsp;&nbsp; | {item.day}{" "}
                &nbsp;&nbsp;&nbsp; | 조회수 : {item.viewship}
              </ContentInfoSect>
            </OneContentDiv>
          </BoardOneContent>
        ))}
      </BoardContentSect>
    </>
  );
};

// 각 데이터를 데이터 구조에 맞게 분리하여서 배열로서 맵핑시켜보아야 함.
