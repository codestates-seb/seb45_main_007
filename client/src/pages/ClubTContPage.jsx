import { styled } from "styled-components";
import { ClubContents } from "../components/ClubContents.jsx";
import { NewHeader } from "../components/NewHeader.jsx";
import boardmarker from "../images/theme/white board marker.jpg";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Pagination from "../components/Pagination.jsx";
import { useParams } from "react-router-dom";
import { BoardFilter } from "../components/BoardFilter.jsx";

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
  background-color: ${(props) => props.color};
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
  position: relative;
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

const NameInputSect = styled.input`
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
  position: absolute;
  right: 0;
  align-items: center;
  justify-content: end;
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
  background: ${(props) => (props.sorted ? "#ffc9c9" : "white")};
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
  width: 40%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const BoardFooterSect = styled.div`
  width: 100%;
  height: 10vh;
  background-color: #fffff0;
`;

const NameInputBtn = styled.div`
  width: 100px;
  height: 40px;
  color: white;
  background-color: black;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  margin-left: 30px;
`;

export const ClubTContPage = () => {
  const { category } = useParams();
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [totalClubData, setTotalClubData] = useState([]);
  const [sortByCreatedAt, setSortByCreatedAt] = useState(false);
  const [searchTitle, setSearchTitle] = useState("");
  const [sortByViewState, setSortByViewState] = useState(false);

  const CategoryTitleChange = () => {
    if (category === "comic") {
      return "만화";
    } else if (category === "movie") {
      return "영화";
    } else if (category === "tvshow") {
      return "TV 프로그램";
    } else if (category === "music") {
      return "노래";
    } else if (category === "item") {
      return "추억 아이템";
    } else if (category === "game") {
      return "게임";
    }
  };

  const BoardTitleLabelColorChange = () => {
    if (category === "comic") {
      return "#800000";
    } else if (category === "movie") {
      return "#ff4500";
    } else if (category === "tvshow") {
      return "#cccc00";
    } else if (category === "music") {
      return "#00008b";
    } else if (category === "game") {
      return "#4b0082";
    } else if (category === "item") {
      return "#006400";
    }
  };

  const SearchInputChange = (event) => {
    const newValue = event.target.value;
    setSearchTitle(newValue);
  };

  const SearchTitleBtnClick = async (searchTitle) => {
    const response = await axios.get(
      `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/clubBoards/searches?page=1&size=10&keyword=${searchTitle}`,
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
      setCurrentPage(1);
      console.log("검색됨");
    } else {
      console.log("error");
    }
  };

  const apiUrl = `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/clubBoards/category?page=1&size=10&category=${category}`;
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
          setTotalPages(response.data.pageInfo.totalPages);
        } else {
          console.log("데이터 가져오기 실패");
        }
      } catch (error) {
        console.log("error");
      }
    }
    fetchData();
  }, [category]);

  const changePage = async (newPage) => {
    const response = await axios.get(
      `http://ec2-13-209-7-250.ap-northeast-2.compute.amazonaws.com/clubBoards/category?page=${newPage}&size=10&category=${category}`,
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
      console.log("Not 200");
    }
  };

  const sortDataByCreatedAt = () => {
    const sortedData = [...totalClubData].sort((a, b) => {
      return new Date(a.createdAt) - new Date(b.createdAt);
    });
    setTotalClubData(sortedData);
  };

  const handleSortByCreatedAt = () => {
    if (!sortByViewState) {
      sortDataByCreatedAt();
      setSortByCreatedAt(true);
      setSortByViewState(false);
    } else {
      console.log("Not 200");
    }
  };

  const sortDataByViewAt = () => {
    const sortedData = [...totalClubData].sort((b, a) => {
      return a.viewCount - b.viewCount;
    });
    setTotalClubData(sortedData);
  };

  const handleSortByViewAt = () => {
    if (!sortByViewState) {
      sortDataByViewAt();
      setSortByCreatedAt(false);
      setSortByViewState(true);
    } else {
      console.log("Not 200");
    }
  };

  return (
    <>
      <NewHeader />

      <BoardTotalContainer>
        <BoardFilterSect>
          <BoardFilter />
        </BoardFilterSect>

        <BoardContentSect>
          <BoardNameSect>
            <TitleSect>
              <TitleLabel color={BoardTitleLabelColorChange()} />
              <TitleText>{CategoryTitleChange()}</TitleText>
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
              <NameInputSect value={searchTitle} onChange={SearchInputChange} />
              <NameInputBtn onClick={() => SearchTitleBtnClick(searchTitle)}>
                검색
              </NameInputBtn>
              <SortFilterSect>
                <SortFilterBtn
                  sorted={sortByViewState}
                  onClick={handleSortByViewAt}
                >
                  조회순
                </SortFilterBtn>
                <SortFilterBtn
                  sorted={sortByCreatedAt}
                  onClick={handleSortByCreatedAt}
                >
                  작성일순
                </SortFilterBtn>
              </SortFilterSect>
            </SearchBarSect>
          </BoardNameSect>

          <BoardContentCont>
            <ClubContents totalClubData={totalClubData} />
            <PaginationBtnSect>
              <PaginationBtnBox>
                <Pagination
                  currentPage={currentPage}
                  totalPages={totalPages}
                  onPageChange={changePage}
                />
              </PaginationBtnBox>
            </PaginationBtnSect>
          </BoardContentCont>
        </BoardContentSect>
      </BoardTotalContainer>
      <BoardFooterSect />
    </>
  );
};
