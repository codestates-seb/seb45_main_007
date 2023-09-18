import styled from "styled-components";
import React, { useState } from "react";
import PropTypes from "prop-types";
export default function CategoryDropdown({ setSelectedCategory }) {
  const category = ["전체", "게임", "만화", "TV", "노래", "영화", "추억아이템"];
  const [isOpen, setIsOpen] = useState(false);
  CategoryDropdown.propTypes = {
    setSelectedCategory: PropTypes.func.isRequired,
  };

  return (
    <Container>
      <ListContainer onClick={() => setIsOpen(!isOpen)}>
        카테고리{isOpen ? "⌃" : "⌄"}
      </ListContainer>
      <DropdownMenu isOpen={isOpen}>
        {category.map((value) => {
          return (
            <List key={value} onClick={() => setSelectedCategory(value)}>
              {value}
            </List>
          );
        })}
      </DropdownMenu>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
`;
const ListContainer = styled.ul`
  list-style: none;
`;
const DropdownMenu = styled.div`
  visibility: ${(props) => (props.isOpen ? "visible" : "hidden")};
  border: 0.5px solid black;
`;

const List = styled.li`
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "ChosunKm";
`;
