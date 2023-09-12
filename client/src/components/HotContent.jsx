import { styled } from "styled-components";
import digiImg from "../images/theme/digimon.jpeg";
import pokeImg from "../images/theme/pokeimg1.jpeg";
import onepieceImg from "../images/theme/luppy.jpeg";

const BoardOneSect = styled.section`
  width: 100%;
  height: 55vh;
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const BoardOneTitle = styled.div`
  width: 18%;
  height: 10%;
  border-bottom: 3px solid #fccacaff;
  font-size: 22px;
  margin-top: 2%;
  text-align: center;
  color: white;
`;

const BoardNoteSect = styled.div`
  width: 100%;
  height: 85%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const BoardNote = styled.div`
  width: 30%;
  height: 70%;
  border-radius: 10px;
  background-color: #f8f8cd;
  border-bottom: 5px solid black;
  margin-top: 1%;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  align-items: center;
  color: white;
  font-size: 50px;
  text-align: center;
  margin-left: 3%;
  overflow: hidden;
  position: relative;
  margin-bottom: 1%;

  &:hover {
    border: 10px solid #9bfff5;
  }
`;

const BoardNoteTitle = styled.h3`
  font-size: 18px;
  width: 100%;
  height: 20%;
  color: black;
  display: flex;
  align-items: flex-end;
  justify-content: center;
`;

const BoardNoteCircle = styled.div`
  border-radius: 50%;
  background-color: white;
  width: 110%;
  height: 100%;
  top: 30%;
  position: absolute;
  z-index: 1001;
  color: black;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  overflow: hidden;
`;

const BoardCircleImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: top center;
  filter: brightness(0.9);
`;

export const HotContent = () => {
  return (
    <>
      <BoardOneSect>
        <BoardOneTitle>우리반 인기글</BoardOneTitle>
        <BoardNoteSect>
          <BoardNote>
            <BoardNoteTitle>디지몬 어드벤처 비디오 팔아요</BoardNoteTitle>

            <BoardNoteCircle>
              <BoardCircleImg src={digiImg} />
            </BoardNoteCircle>
          </BoardNote>

          <BoardNote>
            <BoardNoteTitle>포켓몬 스티커 팔아요</BoardNoteTitle>

            <BoardNoteCircle>
              <BoardCircleImg
                src={pokeImg}
                style={{ objectPosition: "bottom center" }}
              />
            </BoardNoteCircle>
          </BoardNote>

          <BoardNote>
            <BoardNoteTitle>원피스 피규어 팔아요</BoardNoteTitle>

            <BoardNoteCircle>
              <BoardCircleImg src={onepieceImg} />
            </BoardNoteCircle>
          </BoardNote>
        </BoardNoteSect>
      </BoardOneSect>
    </>
  );
};
