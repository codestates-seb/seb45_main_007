import { styled } from "styled-components";
import { NavLink } from "react-router-dom/dist";

const BoardHeaderSect = styled.section`
  width: 100vw;
  height: 72px;
  background-color: white;
  display: flex;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1002;
  flex-direction: column;
`;

const NavLinkSection = styled.div`
  width: 100%;
  height: 100%;
  align-items: center;
  display: flex;
  background-color: gray;
  /* &:hover {
    background-color: #2240b6;
    color: white;
  } */
`;

const NavUl = styled.ul`
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const NavLi = styled.li`
  width: 13%;
  height: 100%;
  font-size: 22px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 0.5%;
  color: white;
  box-sizing: border-box;
  cursor: pointer;
  &:hover {
    background-color: #f4f7f6;
  }
`;

const StampBox = styled.div`
  height: 100%;
  width: 5%;
  background-color: #ffbcbc;
  position: relative;
  overflow: hidden;
`;

const TopLineBox = styled.div`
  width: 100%;
  height: 30%;
  position: absolute;
  top: -15%;
  left: 0;
  display: flex;
`;

const Circle = styled.div`
  width: 20%;
  height: 80%;
  border-radius: 50%;
  background-color: white;
  margin-right: 2%;
`;

// const FilmSect = styled.div`
//   width: 100%;
//   background-color: blue;
//   height: 20%;
// `

// const FilmLine = styled.div`
//   width: 100%;
//   background-color: black;
//   height: 25%;
// `;

// const FilmBoxSect = styled.div`
//   width: 100%;
//   height: 50%;
//   background-color: black;
//   display: flex;
// `;

// const FilmWhiteBox = styled.div`
//   width: 8px;
//   height: 8px;
//   background-color: white;
//   margin-right: 5px;
// `;

export const NewHeader = () => {
  return (
    <>
      <BoardHeaderSect>
        <NavLinkSection>
          <NavUl>
            <NavLi>
              <NavLink to="/">메인</NavLink>
            </NavLi>

            <NavLi>
              <NavLink to="/SchoolHome">바자회</NavLink>
            </NavLi>

            <NavLi>
              <NavLink to="/board">게시판</NavLink>
            </NavLi>

            <NavLi>
              <NavLink to="/board">마이페이지</NavLink>
            </NavLi>
          </NavUl>

          <StampBox>
            <TopLineBox>
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
              <Circle />
            </TopLineBox>
          </StampBox>
        </NavLinkSection>
      </BoardHeaderSect>
    </>
  );
};
