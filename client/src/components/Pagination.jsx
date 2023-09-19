import React from "react";
import { styled } from "styled-components";
import arrowImg from "../images/leftarrow.png";

const PageArrowBtnBox = styled.div`
  width: 25px;
  height: 25px;
  margin-right: 50px;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
`;

const PageArrow1 = styled.img`
  width: 100%;
  height: 100%;
`;

const PageArrow2 = styled.img`
  width: 100%;
  height: 100%;
  transform: rotate(180deg);
`;

const PageArrowBtnBox2 = styled.div`
  width: 25px;
  height: 25px;
  margin-left: 50px;
  font-size: 18px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
`;

const PaginationNumberSect = styled.div`
  width: 30px;
  height: 30px;
  border-radius: 50%;
  align-items: center;
  justify-content: center;
  display: flex;
  font-size: 22px;
  background-color: #ffe3de;
  margin-left: 10px;
  cursor: pointer;
  &:hover {
    background-color: black;
    color: white;
  }
`;

/* eslint-disable react/prop-types */
function Pagination({ currentPage, totalPages, onPageChange }) {
  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(
        /* eslint-disable jsx-a11y/click-events-have-key-events, jsx-a11y/no-static-element-interactions */
        <PaginationNumberSect
          key={i}
          onClick={() => onPageChange(i)}
          className={currentPage === i ? "active" : ""}
        >
          {i}
        </PaginationNumberSect>,
      );
    }
    return pageNumbers;
  };

  return (
    <>
      <PageArrowBtnBox
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 1}
      >
        <PageArrow1 src={arrowImg} />
      </PageArrowBtnBox>

      {renderPageNumbers()}
      <PageArrowBtnBox2
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage === totalPages}
      >
        <PageArrow2 src={arrowImg} />
      </PageArrowBtnBox2>
    </>
  );
}

export default Pagination;
