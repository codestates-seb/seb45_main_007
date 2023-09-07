import { styled } from "styled-components";
import toy1 from "../images/theme/toy1.png";
import toy2 from "../images/theme/car.png";
import toy3 from "../images/theme/doll.png";
import toy4 from "../images/theme/game-console.png";
import textbook from "../images/theme/textbook.jpeg";
import stich from "../images/theme/sticihimg.png";
import sil from "../images/theme/cats.png";

const BoardContentSect = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  margin-bottom: 5%;
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
  font-size: 18px;
  display: flex;
  align-items: flex-end;
  margin-left: 2%;
`;

const ContentInfoSect = styled.div`
  width: 90%;
  height: 50%;
  display: flex;
  font-size: 13px;
  align-items: center;
  margin-left: 2%;
`;

export const BoardDial = () => {
  return (
    <>
      <BoardContentSect>
        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={toy1} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>포켓몬스터 비디오 가지고 있는 사람?</TitleTextSect>

            <ContentInfoSect>
              작성자 : 이다경 &nbsp;&nbsp;&nbsp; | 2020-04-20 &nbsp;&nbsp;&nbsp;
              | 조회수 : 1993
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>

        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={toy2} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>탑블레이드 아는 놈?</TitleTextSect>

            <ContentInfoSect>
              작성자 : 이형수 &nbsp;&nbsp;&nbsp; | 2020-09-03 &nbsp;&nbsp;&nbsp;
              | 조회수 : 1111
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>

        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={toy3} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>야인시대 놀이 해본 사람?</TitleTextSect>

            <ContentInfoSect>
              작성자 : 유경문 &nbsp;&nbsp;&nbsp; | 2020-06-09 &nbsp;&nbsp;&nbsp;
              | 조회수 : 723
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>

        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={textbook} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>님들 어릴 때 별명 뭐였음?</TitleTextSect>

            <ContentInfoSect>
              작성자 : 박성원 &nbsp;&nbsp;&nbsp; | 2020-01-06 &nbsp;&nbsp;&nbsp;
              | 조회수 : 2012
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>

        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={toy4} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>디아블로2 CD 희귀판 보여줌.</TitleTextSect>

            <ContentInfoSect>
              작성자 : 정창인 &nbsp;&nbsp;&nbsp; | 2020-12-26 &nbsp;&nbsp;&nbsp;
              | 조회수 : 1
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>
        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={sil} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>실뜨기 고수 덤벼라</TitleTextSect>

            <ContentInfoSect style={{ fontSize: "16px" }}>
              작성자 : 유지웅 &nbsp;&nbsp;&nbsp; | 2020-12-26 &nbsp;&nbsp;&nbsp;
              | 조회수 : 1
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>
        <BoardOneContent>
          <OneContentLabelSect>
            <OneContLabelImg src={stich} />
          </OneContentLabelSect>

          <OneContentDiv>
            <TitleTextSect>메이플 전사 공략법좀</TitleTextSect>

            <ContentInfoSect>
              작성자 : 최승연 &nbsp;&nbsp;&nbsp; | 2020-12-26 &nbsp;&nbsp;&nbsp;
              | 조회수 : 1
            </ContentInfoSect>
          </OneContentDiv>
        </BoardOneContent>
      </BoardContentSect>
    </>
  );
};
