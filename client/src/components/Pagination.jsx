import React from "react";
/* eslint-disable react/prop-types */
function Pagination({ currentPage, totalPages, onPageChange }) {
  const renderPageNumbers = () => {
    const pageNumbers = [];
    for (let i = 1; i <= totalPages; i++) {
      pageNumbers.push(
        /* eslint-disable jsx-a11y/click-events-have-key-events, jsx-a11y/no-static-element-interactions */
        <span
          key={i}
          onClick={() => onPageChange(i)}
          className={currentPage === i ? "active" : ""}
        >
          {i}
        </span>,
      );
    }
    return pageNumbers;
  };

  return (
    <div className="pagination">
      <button
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 1}
      >
        이전
      </button>
      {renderPageNumbers()}
      <button
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage === totalPages}
      >
        다음
      </button>
    </div>
  );
}

export default Pagination;
