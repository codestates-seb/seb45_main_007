import { styled } from "styled-components";
import { ClubContents } from "../components/ClubContents.jsx";
import { NewHeader } from "../components/NewHeader.jsx";
import boardmarker from "../images/theme/white board marker.jpg";
import { ClubMockData } from "../data/ClubMockData.js";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Pagination from "../components/Pagination.jsx";
import { useParams } from "react-router-dom";

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
  margin-top: 7%;
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

const TitleSect = styled.div`
  width: 20%;
  height: 20%;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
`;

const TitleLabel = styled.div`
  height: 7%;
  width: 40%;
  background-color: #c40505;
  margin-bottom: 0.7%;
`;

const TitleText = styled.div`
  height: 80%;
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
  height: 20%;
  margin-top: 50px;
  text-align: center;
  font-size: 22px;
`;

const HotBtnSect = styled.div`
  width: 80%;
  height: 40%;
  display: flex;
  margin-top: 1%;
  justify-content: center;
`;

const HotBtn = styled.div`
  cursor: pointer;
  width: 10%;
  margin-left: 1%;
  height: 60%;
  border-radius: 15px;
  background-color: #718be8;
  border: 1px solid blue;
  color: white;
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
  width: 30%;
  height: 90%;
  display: flex;
  margin-left: 350px;
`;

const SortFilterBtn = styled.div`
  width: 100px;
  height: 90%;
  border: 1px solid gray;
  border-radius: 5px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  padding: 15px;
  margin-left: 10px;
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

// const OnePageBtn = styled.div`
//   width: 12%;
//   height: 85%;
//   margin-right: 3%;
//   border: 3px solid #718be8;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   font-size: 18px;
//   color: #718be8;
//   cursor: pointer;
//   border-radius: 10px;
// `;

// const OnePageBtnSelected = styled.div`
//   width: 12%;
//   height: 85%;
//   margin-right: 3%;
//   border: 3px solid black;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   font-size: 18px;
//   color: black;
//   cursor: pointer;
//   border-radius: 10px;
// `;

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
  const { category } = useParams();
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [totalClubData, setTotalClubData] = useState([]);
  const apiUrl = `https://9dac-2406-5900-705c-f80b-2c90-ee5-6e07-7434.ngrok-free.app/clubBoards?page=1&size=10`;
  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get(apiUrl, {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        });

        if (response.status === 200) {
          setTotalClubData(response.data.clubBoards);
          console.log(totalClubData);
          setTotalPages(response.data.pageInfo.totalPages);
        } else {
          console.error("데이터 가져오기 실패");
        }
      } catch (error) {
        const filteredClubData = ClubMockData.filter(
          (data) => data.clubBoards.category === category,
        );
        setTotalClubData(filteredClubData.slice(0, 10));
        setTotalPages(Math.ceil(filteredClubData.length / 10));
      }
    }
    fetchData();
  }, []);

  const changePage = async (newPage) => {
    const response = await axios.get(
      `https://9dac-2406-5900-705c-f80b-2c90-ee5-6e07-7434.ngrok-free.app/clubBoards?page=${newPage}&size=10`,
      {
        headers: {
          "Content-Type": "application/json",
          "ngrok-skip-browser-warning": "69420",
        },
      },
    );

    if (response.status === 200) {
      setTotalClubData(response.data.clubBoards);
      setTotalPages(response.data.pageInfo.totalPages);
      setCurrentPage(newPage);
    } else {
      const filteredClubData = ClubMockData.filter(
        (data) => data.clubBoards.category === category,
      );
      const startIdx = newPage * 10 - 10 + 1;
      const sliceClubData = filteredClubData.slice(startIdx, startIdx + 10);
      setTotalClubData(sliceClubData);
      setTotalPages(Math.ceil(filteredClubData.length / 10));
      setCurrentPage(newPage);
    }
  };

  return (
    <>
      <NewHeader />

      <BoardTotalContainer>
        <BoardFilterSect>
          <BoardFilterBox>
            <BoardFilterThreeBox>
              <ThreeBoxTitle>게시판 이동하기</ThreeBoxTitle>
              <ThreeBoxContent>
                만화
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
              <SortFilterSect>
                <SortFilterBtn>조회순</SortFilterBtn>
                <SortFilterBtn>작성일순</SortFilterBtn>
                <SortFilterBtn>추천순</SortFilterBtn>
              </SortFilterSect>
            </SearchBarSect>
          </BoardNameSect>

          <BoardContentCont>
            <ClubContents totalClubData={totalClubData} />
            <PaginationBtnSect>
              <PaginationBtnBox>
                <PageArrowBtnBox>&larr;</PageArrowBtnBox>

                <Pagination
                  currentPage={currentPage}
                  totalPages={totalPages}
                  onPageChange={changePage}
                />
                <PageArrowBtnBox>&rarr;</PageArrowBtnBox>
              </PaginationBtnBox>
            </PaginationBtnSect>
          </BoardContentCont>
        </BoardContentSect>
      </BoardTotalContainer>
      <BoardFooterSect />
    </>
  );
};

// {pageRange.slice(startPage - 1, endPage).map((pageNumber) => {
//   if (currentPage === pageNumber) {
//     return (
//       <OnePageBtnSelected
//         key={pageNumber}
//         onClick={() => handlePageChange(pageNumber)}
//       >
//         {pageNumber}
//       </OnePageBtnSelected>
//     );
//   } else {
//     return (
//       <OnePageBtn
//         key={pageNumber}
//         onClick={() => handlePageChange(pageNumber)}
//       >
//         {pageNumber}
//       </OnePageBtn>
//     );
//   }
// })}
