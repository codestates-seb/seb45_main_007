import { styled } from "styled-components";
import { ClubContents } from "../components/ClubContents.jsx";
import { NewHeader } from "../components/NewHeader.jsx";
import boardmarker from "../images/theme/white board marker.jpg";
import { ClubBasicData } from "../data/ClubBasicData.js";
import React, { useState } from "react";

const BoardTotalContainer = styled.section`
  width: 100vw;
  overflow: hidden;
  display: flex;
  position: relative;
`;

const BoardContentSect = styled.section`
  width: 75%;
  display: flex;
  flex-direction: column;
`;

const BoardNameSect = styled.section`
  margin-top: 6%;
  width: 100%;
  height: 45vh;
  display: flex;
  flex-direction: column;
`;

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
  height: 50%;
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
  font-size: 13px;
  border-bottom: 0.001px solid black;
  cursor: pointer;
`;

const TitleSect = styled.div`
  width: 20%;
  height: 20%;
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

const HotBoard = styled.div`
  width: 100%;
  height: 60%;
  border-radius: 10px;
  border: 8px solid gray;
  position: relative;
  margin-top: 0%;
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

const SearchBarSect = styled.section`
  width: 100%;
  height: 10%;
  margin-top: 2.5%;
  display: flex;
`;

const SearchBarNameFilter = styled.div`
  width: 13%;
  height: 90%;
  border: 1px solid black;
  border-radius: 5px;
  display: flex;
  align-items: center;
  font-size: 18px;
  padding: 15px;
`;

const NameInputSect = styled.div`
  width: 35%;
  height: 90%;
  border-bottom: 1px solid gray;
  border-radius: 5px;
  display: flex;
  align-items: center;
  font-size: 13px;
  padding: 15px;
  margin-left: 3%;
`;

const SortFilterSect = styled.div`
  width: 7%;
  height: 90%;
  border: 1px solid gray;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  padding: 15px;
  margin-left: 43%;
  cursor: pointer;
`;

const BoardContentCont = styled.section`
  width: 100%;
  height: auto;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  background-color: white;
`;

const BoardMarkerImg = styled.img`
  width: 7%;
  height: 40%;
  position: absolute;
  bottom: 5%;
  right: 6%;
`;

const PaginationBtnSect = styled.div`
  width: 100%;
  height: 5vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const PaginationBtnBox = styled.div`
  width: 25%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const OnePageBtn = styled.div`
  width: 12%;
  height: 85%;
  margin-right: 3%;
  border: 3px solid #718be8;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  color: #718be8;
  cursor: pointer;
  border-radius: 10px;
`;

const OnePageBtnSelected = styled.div`
  width: 12%;
  height: 85%;
  margin-right: 3%;
  border: 3px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  color: black;
  cursor: pointer;
  border-radius: 10px;
`;

// 선택된 버튼만 필터링할 수 있도록 수정하기!

const PageArrowBtnBox = styled.div`
  width: 12%;
  height: 85%;
  margin-right: 2%;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const BoardFooterSect = styled.div`
  width: 100%;
  height: 10vh;
  background-color: #fffff0;
`;

export const ClubTContPage = () => {
  const totalPosts = ClubBasicData.length;
  console.log(`totalPostNum: ${totalPosts}`);
  const postsPerPage = 10;
  const totalPages = Math.ceil(totalPosts / postsPerPage);
  const [currentPage, setCurrentPage] = useState(1);
  const pagesToShow = 5;
  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const pageRange = Array.from({ length: totalPages }, (_, index) => index + 1);
  const startPage = Math.max(1, currentPage - Math.floor(pagesToShow / 2));
  const endPage = Math.min(totalPages, startPage + pagesToShow - 1);
  const startIndex = (currentPage - 1) * postsPerPage;
  const endIndex = startIndex + postsPerPage;
  const currentPosts = ClubBasicData.slice(startIndex, endIndex);

  // 다섯개의 버튼씩 따른 분리 해보기, 다른 정보 들어오게 해보기, 페이지네이션 코드 암기 및 코드분리 기획하기 및 전체상태관리에 대한 생각 및 구상. 전체 로직 암기. 검색기능 구현에 대한 기획하기. 라우팅 분리하기

  return (
    <>
      <NewHeader />

      <BoardTotalContainer>
        <BoardFilterSect>
          <BoardFilterBox>
            <BoardFilterThreeBox>
              <ThreeBoxTitle>물건 카테고리</ThreeBoxTitle>
              <ThreeBoxContent>만화영화</ThreeBoxContent>
              <ThreeBoxContent>음악</ThreeBoxContent>
              <ThreeBoxContent>물건</ThreeBoxContent>
              <ThreeBoxContent>노래</ThreeBoxContent>
              <ThreeBoxContent>노래</ThreeBoxContent>
            </BoardFilterThreeBox>
            <BoardFilterThreeBox>
              <ThreeBoxTitle>물건 카테고리</ThreeBoxTitle>
              <ThreeBoxContent>만화영화</ThreeBoxContent>
              <ThreeBoxContent>음악</ThreeBoxContent>
              <ThreeBoxContent>물건</ThreeBoxContent>
              <ThreeBoxContent>노래</ThreeBoxContent>
              <ThreeBoxContent>노래</ThreeBoxContent>
            </BoardFilterThreeBox>
          </BoardFilterBox>
        </BoardFilterSect>

        <BoardContentSect>
          <BoardNameSect>
            <TitleSect>
              <TitleLabel />
              <TitleText>만화</TitleText>
            </TitleSect>

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

            <SearchBarSect>
              <SearchBarNameFilter>제목</SearchBarNameFilter>
              <NameInputSect>검색어를 입력하세요</NameInputSect>
              <SortFilterSect>조회순</SortFilterSect>
            </SearchBarSect>
          </BoardNameSect>

          <BoardContentCont>
            <ClubContents currentPosts={currentPosts} />
            <PaginationBtnSect>
              <PaginationBtnBox>
                <PageArrowBtnBox>&larr;</PageArrowBtnBox>

                {pageRange.slice(startPage - 1, endPage).map((pageNumber) => {
                  if (currentPage === pageNumber) {
                    return (
                      <OnePageBtnSelected
                        key={pageNumber}
                        onClick={() => handlePageChange(pageNumber)}
                      >
                        {pageNumber}
                      </OnePageBtnSelected>
                    );
                  } else {
                    return (
                      <OnePageBtn
                        key={pageNumber}
                        onClick={() => handlePageChange(pageNumber)}
                      >
                        {pageNumber}
                      </OnePageBtn>
                    );
                  }
                })}
                <PageArrowBtnBox>&rarr;</PageArrowBtnBox>
              </PaginationBtnBox>
            </PaginationBtnSect>
          </BoardContentCont>

          <BoardFooterSect />
        </BoardContentSect>
      </BoardTotalContainer>
    </>
  );
};
