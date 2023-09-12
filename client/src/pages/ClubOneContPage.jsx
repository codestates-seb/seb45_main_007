import { styled } from "styled-components";
import { NewHeader } from "../components/NewHeader.jsx";

const BoardOneContContainer = styled.section`
  width: 100%;
  display: flex;
`;

const BoardOneFilterSect = styled.article`
  width: 12.5%;
  height: 150vh;
  margin-top: 7.2vh;
`;

const BoardOneContentSect = styled.article`
  width: 75%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 7.2vh;
`;

const BoardUpSect = styled.div`
  width: 100%;
  height: 10vh;
`;

const BoardBottomSect = styled.section`
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const BoardTitleSect = styled.article`
  width: 100%;
  height: 10vh;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const BoardTitleBox = styled.div`
  width: 100%;
  height: 100%;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const WritedTitleBox = styled.div`
  width: 100%;
  height: 50%;
  background-color: yellow;
  display: flex;
  align-items: center;
  border-bottom: 1px solid black;
`;

const LeftTitleSect = styled.div`
  width: 10%;
  height: 100%;
  background-color: white;
  display: flex;
  align-items: center;
  padding-left: 50px;
  font-size: 16px;
`;

const CenterTitleBox = styled.div`
  width: 70%;
  height: 100%;
  display: flex;
  align-items: center;
  font-size: 16px;
  background-color: white;
  padding-left: 30px;
`;

const RightInfoBox = styled.div`
  width: 20%;
  height: 100%;
  background-color: white;
  align-items: center;
  justify-content: center;
  display: flex;
  font-size: 16px;
`;

const WritedInfoBox = styled.div`
  width: 100%;
  height: 50%;
  background-color: white;
  display: flex;
`;

const LeftWirterBox = styled.div`
  width: 10%;
  height: 100%;
  background-color: white;
  display: flex;
  align-items: center;
  padding-left: 50px;
  font-size: 16px;
`;

const RightWirterInfoBox = styled.div`
  width: 50%;
  background-color: white;
  padding-left: 30px;
  font-size: 16px;
  display: flex;
  align-items: center;
`;

const OneBoardFooterSect = styled.div`
  width: 100%;
  height: 15vh;
  background-color: #fffff0;
`;

const BasicSizeContentBox = styled.section`
  width: 100%;
  border-bottom: 2px solid black;
  font-size: 16px;
  padding-left: 40px;
`;

const NextContentSect = styled.section`
  width: 100%;
  height: 10vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-top: 2%;
`;

const PrevContBox = styled.div`
  width: 100%;
  height: 50%;
  display: flex;
  align-items: center;
  font-size: 16px;
  border-bottom: 1 px solid black;
  border-top: 3px solid black;
`;

const PrevLeft = styled.div`
  width: 15%;
  height: 100%;
  font-size: 16px;
  align-items: center;
  display: flex;
  justify-content: center;
  border-right: 1px solid black;
`;

const PrevCenter = styled.div`
  width: 70%;
  height: 100%;
  background-color: white;
  display: flex;
  align-items: center;
  padding-left: 50px;
  cursor: pointer;
  border-right: 1px solid black;
`;

const PrevRight = styled.div`
  width: 15%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const NextContBox = styled.div`
  width: 100%;
  height: 50%;
  display: flex;
  align-items: center;
  font-size: 16px;
  border-bottom: 3px solid black;
`;

const BoardOneCommentSect = styled.article`
  width: 100%;
  margin-top: 5%;
  display: flex;
  flex-direction: column;
  border-bottom: 3px solid brown;
  border-top: 3px solid brown;
`;

const BoardOneCommentOne = styled.div`
  width: 100%;
  height: 4vh;
  display: flex;
  align-items: center;
  font-size: 16px;
  border-bottom: 1px solid gray;
`;

const CommentOneLeft = styled.div`
  width: 15%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  border-right: 1px solid black;
`;

const CommentOneCenter = styled.div`
  width: 70%;
  height: 100%;
  display: flex;
  align-items: center;
  padding-left: 30px;
  font-size: 16px;
  border-right: 1px solid black;
`;

const CommentOneRight = styled.div`
  width: 15%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const CommentWriteBoxSect = styled.article`
  width: 100%;
  height: 20vh;
  margin-top: 1%;
  border-top: 2px solid gray;
  border-bottom: 2px solid gray;
  display: flex;
  flex-direction: column;
`;

const CommentUpSect = styled.div`
  background-color: white;
  height: 70%;
  width: 100%;
  display: flex;
`;

const CommentBottomSect = styled.div`
  width: 100%;
  height: 30%;
  margin-bottom: 2px;
  margin-top: 2px;
  display: flex;
  justify-content: end;
  align-items: center;
`;

const CommentBottomBtn = styled.div`
  width: 7%;
  height: 65%;
  background-color: aquamarine;
  border: 1px solid black;
  margin-right: 4.2%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1px;
  border-radius: 10px;
`;

const CommentUpLeft = styled.div`
  width: 15%;
  height: 100%;
  align-items: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;

const CommentUserBox = styled.div`
  width: 75%;
  height: 30%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid gray;
  margin-bottom: 5%;
  border-radius: 5px;
`;

const CommentPassBox = styled.div`
  width: 75%;
  height: 30%;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid gray;
  border-radius: 5px;
`;

const CommentUpRight = styled.div`
  width: 85%;
  height: 100%;
  margin-top: 1%;
`;

const CommentRightInputBox = styled.div`
  width: 95%;
  height: 90%;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 5px;
  border: 1px solid gray;
`;

export const ClubOneContPage = () => {
  return (
    <>
      <NewHeader />
      <BoardOneContContainer>
        <BoardOneFilterSect>필터링 영역임</BoardOneFilterSect>
        <BoardOneContentSect>
          <BoardUpSect />
          <BoardBottomSect>
            <BoardTitleSect>
              <BoardTitleBox>
                <WritedTitleBox>
                  <LeftTitleSect>제목</LeftTitleSect>
                  <CenterTitleBox>디지몬 어드벤처 물건 팔아요</CenterTitleBox>
                  <RightInfoBox>2023-09-05 | 조회수 102</RightInfoBox>
                </WritedTitleBox>
                <WritedInfoBox>
                  <LeftWirterBox>작성자</LeftWirterBox>
                  <RightWirterInfoBox>정창인</RightWirterInfoBox>
                </WritedInfoBox>
              </BoardTitleBox>
            </BoardTitleSect>

            <BasicSizeContentBox>
              미디어커뮤니케이션대학원에서 함께 근무할 조교를 모집합니다.
              <br />
              <br />
              <br />
              <br />
              <br />
              1. 모집분야 및 인원 가. 모집분야 : 조교 Ⅰ형 나. 모집인원 : 0명
              <br />
              <br />
              <br />
              <br />
              <br />
              <br />
              <br />
              <br />
              <br />
              <br />
              2. 지원자격 가. 본교 대학원 재학생 및 입학예정자
              <br />
              3. 근무조건 가. 근무시간 : Ⅰ유형 (주14시간) ※ 경희대학교 조교관리
              규정에 의함
              <br />
              <br />
              <br />
              <br />
              나. 처우 : 수업료의 80% 감면 다. 업무내용 : 사무보조
              <br />
              <br />
              <br />
              <br />
              라. 필수사항 : 화요일 또는 목요일 야간근무(18:00 ~ 22:00) 가능자
              <br />
              4. 제출서류
              <br />
              <br />
              <br />
              가. 이력서 및 자기소개서 1부(본교 소정양식_붙임파일 참조)
              <br />
              <br />
              <br />
              나. 성적증명서, 재학증명서(신입생의 경우 합격증) 각 1부
              <br />
              <br />
              <br />
              ※ 최종합격자에 한함, 제출된 서류는 반환하지 않습니다.
              <br />
              <br />
              <br />
              5. 전형방법
              <br />
              가. 1차 서류전형 나. 2차 면접전형 ※ 서류전형 합격자에 한해 면접
              실시(개별통보)
              <br />
              6. 접수 기간 및 방법 가. 접수 기간 : 2023.09.05(화) ~ 09.12(화)
              까지
              <br />
              <br />
              <br />
              <br />
              <br />
              나. 접수 방법 : E-mail 접수 khsb2520@khu.ac.kr - 메일제목 :
              [홍길동] 조교 지원 - 첨부파일명 : [홍길동] 이력서 및 자기소개서 7.
              문의 : 02-961-0560~1 / 청운관 606호 * 첨부 : 이력서 및 자기소개서
              1부
            </BasicSizeContentBox>

            <NextContentSect>
              <PrevContBox>
                <PrevLeft>이전글</PrevLeft>
                <PrevCenter>포켓몬 띠부띠부실 팔아요</PrevCenter>
                <PrevRight>2023-09-05</PrevRight>
              </PrevContBox>
              <NextContBox>
                <PrevLeft>다음글</PrevLeft>
                <PrevCenter>디지몬 어드벤처 카드 얼마함?</PrevCenter>
                <PrevRight>2023-09-05</PrevRight>
              </NextContBox>
            </NextContentSect>

            <BoardOneCommentSect>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
              <BoardOneCommentOne>
                <CommentOneLeft>작성자</CommentOneLeft>
                <CommentOneCenter>내가 살게요</CommentOneCenter>
                <CommentOneRight>2023-09-05</CommentOneRight>
              </BoardOneCommentOne>
            </BoardOneCommentSect>

            <CommentWriteBoxSect>
              <CommentUpSect>
                <CommentUpLeft>
                  <CommentUserBox />
                  <CommentPassBox />
                </CommentUpLeft>
                <CommentUpRight>
                  <CommentRightInputBox />
                </CommentUpRight>
              </CommentUpSect>

              <CommentBottomSect>
                <CommentBottomBtn />
              </CommentBottomSect>
            </CommentWriteBoxSect>
          </BoardBottomSect>
        </BoardOneContentSect>
      </BoardOneContContainer>
      <OneBoardFooterSect />
    </>
  );
};
