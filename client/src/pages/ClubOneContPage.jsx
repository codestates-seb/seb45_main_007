import { styled } from "styled-components";
import { NewHeader } from "../components/NewHeader.jsx";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import { ClubMockData } from "../data/ClubMockData.js";
import { ClubCommentData } from "../data/ClubCommentData.js";

const BoardOneContContainer = styled.section`
  width: 100%;
  display: flex;
  margin-bottom: 10vh;
`;

const BoardOneFilterSect = styled.article`
  width: 12.5%;
  height: 100vh;
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
  margin-top: 50px;
  display: flex;
  position: relative;
`;

const PrevBoardBtn = styled.div`
  width: 100px;
  height: 50px;
  border-radius: 15px;
  background-color: #e6e6ff;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 20px;
`;

const BoardTitleLabel = styled.div`
  height: 7%;
  width: 100px;
  background-color: ${(props) => props.color};
  margin-bottom: 0.7%;
`;

const BoardTitleText = styled.div`
  height: 80%;
  width: 100%;
  font-size: 32px;
  letter-spacing: 20px;
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
  height: 70%;
  background-color: yellow;
  display: flex;
  align-items: center;
  border-bottom: 1px solid black;
  border-top: 3px solid red;
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
  font-size: 22px;
  background-color: white;
  padding-left: 30px;
`;

const EditingCenterTitleBox = styled.input`
  width: 70%;
  height: 100%;
  display: flex;
  align-items: center;
  font-size: 22px;
  background-color: white;
  padding-left: 30px;
  &:focus {
    border: 10px solid black;
  }
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
  height: 30%;
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

const COneContTextBox = styled.article`
  width: 100%;
  margin-top: 30px;
  margin-bottom: 50px;
  line-height: 1.5;
  font-size: 16px;
`;

const EditingCOneContTextBox = styled.input`
  width: 100%;
  margin-top: 30px;
  margin-bottom: 50px;
  line-height: 1.5;
  font-size: 16px;
  &:focus {
    border: 10px solid black;
  }
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
  position: relative;
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
  cursor: pointer;
  background-color: #7fceff;
  border: 1px solid black;
  margin-right: 4.2%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 1px;
  border-radius: 10px;
  font-size: 16px;
`;

const CommentUpLeft = styled.div`
  width: 15%;
  height: 100%;
  align-items: center;
  display: flex;
  flex-direction: column;
`;

const CommentUserBox = styled.div`
  width: 150px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid gray;
  border-radius: 5px;
  font-size: 16px;
  margin-top: 15px;
`;

// const CommentPassBox = styled.div`
//   width: 75%;
//   height: 30%;
//   display: flex;
//   align-items: center;
//   justify-content: center;
//   border: 1px solid gray;
//   border-radius: 5px;
// `;

const CommentUpRight = styled.div`
  width: 85%;
  height: 100%;
  margin-top: 1%;
`;

const CommentRightInputBox = styled.input`
  width: 95%;
  height: 90%;
  display: flex;
  font-size: 22px;
  justify-content: center;
  border-radius: 5px;
  border: 1px solid gray;
  padding: 30px;
`;

const SubComment = styled.div`
  width: 100%;
  height: 50px;
  border-bottom: 1px solid gray;
  display: flex;
  align-items: center;
  padding: 30px;
  font-size: 13px;
`;

const SubCommentPlus = styled.div`
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #e2e2ff;
  position: absolute;
  right: 0;
`;

const SubcommentInput = styled.input`
  width: 100%;
  height: 50px;
  border-bottom: 1px solid gray;
  display: flex;
  align-items: center;
  padding: 30px;
  font-size: 13px;
`;

const LikeBtn = styled.div`
  width: 50px;
  height: 50px;
  background-color: blue;
`;

export const ClubOneContPage = () => {
  // const navigate = useNavigate();
  const { clubBoardId } = useParams();
  const { category } = useParams();
  const [oneClubData, setOneClubData] = useState([]);
  const OneContApiUrl = `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}`;

  const [editingState, setEditingState] = useState(false);
  const [editingTitle, setEditingTitle] = useState(oneClubData.title);
  const [editingContent, setEditingContent] = useState(oneClubData.content);
  const [baseCommentData, setBaseCommentData] = useState([...ClubCommentData]);
  const [insertComment, setInserComment] = useState("");

  // 추천수 늘리는 코드

  // const sendLikeRequest = async () => {
  //   try {
  //     // 요청을 보낼 URL
  //     const url = `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}/recommends`;

  //     // POST 요청 보내기
  //     const response = await axios.post(url, postData, {
  //       headers: {
  //         "Content-Type": "application/json",
  //         "ngrok-skip-browser-warning": "69420",
  //       },
  //     });

  //     // 요청이 성공한 경우
  //     console.log("POST 요청 성공:", response.data);
  //   } catch (error) {
  //     // 요청이 실패한 경우
  //     console.error("POST 요청 실패:", error);
  //   }
  // };

  // 좋아요 불러오는 코드

  // const sendLikeReadRequest = async () => {
  //   try {
  //     // 요청을 보낼 URL
  //     const url = `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}/recommends`;

  //     // POST 요청 보내기
  //     const response = await axios.get(url, {
  //       headers: {
  //         "Content-Type": "application/json",
  //         "ngrok-skip-browser-warning": "69420",
  //       },
  //     });

  //     // 요청이 성공한 경우
  //     console.log("POST 요청 성공:", response.data);
  //   } catch (error) {
  //     // 요청이 실패한 경우
  //     console.error("POST 요청 실패:", error);
  //   }
  // };

  // 좋아요 취소하는 코드

  // const sendLikeCancelRequest = async () => {
  //   try {
  //     const url = `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}/recommends`;

  //     const response = await axios.delete(url, {
  //       headers: {
  //         "Content-Type": "application/json",
  //         "ngrok-skip-browser-warning": "69420",
  //       },
  //     });
  //     console.log("POST 요청 성공:", response.data);
  //   } catch (error) {
  //     // 요청이 실패한 경우
  //     console.error("POST 요청 실패:", error);
  //   }
  // };

  const [commentInputStates, setCommentInputStates] = useState(
    baseCommentData.map(() => ({
      subcommentAdd: false,
      subcommentText: "",
    })),
  );

  const toggleSubCommentInput = (index) => {
    setCommentInputStates((prevStates) =>
      prevStates.map((state, i) =>
        i === index ? { ...state, subcommentAdd: !state.subcommentAdd } : state,
      ),
    );
  };

  const handleSubCommentChange = (index, event) => {
    const { name, value } = event.target;
    setCommentInputStates((prevStates) =>
      prevStates.map((state, i) =>
        i === index ? { ...state, [name]: value } : state,
      ),
    );
  };

  const addSubComment = (index) => {
    const updatedCommentData = [...baseCommentData];
    updatedCommentData[index].subComment.push(
      commentInputStates[index].subcommentText,
    );
    setBaseCommentData(updatedCommentData);

    // 입력 상태 초기화
    setCommentInputStates((prevStates) =>
      prevStates.map((state, i) =>
        i === index
          ? { ...state, subcommentAdd: false, subcommentText: "" }
          : state,
      ),
    );
  };

  const InsertCommentFunc = (event) => {
    const newValue = event.target.value;
    setInserComment(newValue);
  };

  const CommentChangeBtnClick = () => {
    const copiedData = [...baseCommentData];
    const newData = {};
    newData.commentId = copiedData.length + 1;
    newData.content = insertComment;
    newData.writer = "추가된 작성자";
    newData.createdAt = "2023-07-06T20:52:28.17253";
    const AddedCommentData = [...copiedData, newData];
    setBaseCommentData(AddedCommentData);
  };

  const SetEditingBtnClick = () => {
    setEditingState(true);
  };

  const EditingTitleFunc = (event) => {
    const newValue = event.target.value;
    setEditingTitle(newValue);
  };

  const EditingContentFunc = (event) => {
    const newValue = event.target.value;
    setEditingContent(newValue);
  };

  const EditingSubmitFunc = () => {
    const copiedData = oneClubData;
    copiedData.title = editingTitle;
    copiedData.content = editingContent;
    setOneClubData(copiedData);
    setEditingState(false);
  };

  // function extractDateFromDateISOString(dateISOString) {
  //   const dateObject = new Date(dateISOString);
  //   const formattedDate = dateObject.toISOString().split("T")[0];
  //   return formattedDate;
  // };

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axios.get(OneContApiUrl, {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        });

        if (response.status === 200) {
          setOneClubData(response.data);
          console.log(response.data);
          console.log(oneClubData);
        } else {
          console.error("200 코드가 아님");
        }
      } catch (error) {
        console.log(clubBoardId);
        const OneData = ClubMockData.clubBoards.filter(
          (data) => data.clubBoardId === Number(clubBoardId),
        );
        setOneClubData(...OneData);
      }
    }
    fetchData();
  }, []);

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
  return (
    <>
      <NewHeader />
      <BoardOneContContainer>
        <BoardOneFilterSect></BoardOneFilterSect>
        <BoardOneContentSect>
          <BoardUpSect>
            <BoardTitleText>
              <BoardTitleLabel color={BoardTitleLabelColorChange} />
              만화
            </BoardTitleText>

            <PrevBoardBtn onClick={SetEditingBtnClick}>
              글 수정 하기
            </PrevBoardBtn>
            <PrevBoardBtn>글 삭제 하기</PrevBoardBtn>
            <Link to={`/club/${category}`}>
              <PrevBoardBtn>글 목록 가기</PrevBoardBtn>
            </Link>
          </BoardUpSect>
          <BoardBottomSect>
            <BoardTitleSect>
              <BoardTitleBox>
                <WritedTitleBox>
                  <LeftTitleSect>제목</LeftTitleSect>
                  {!editingState ? (
                    <CenterTitleBox>{oneClubData.title}</CenterTitleBox>
                  ) : (
                    <EditingCenterTitleBox
                      value={editingTitle}
                      onChange={EditingTitleFunc}
                    ></EditingCenterTitleBox>
                  )}
                  <RightInfoBox>
                    {/* {extractDateFromDateISOString(oneClubData.createdAt)} | */}
                    조회수 {oneClubData.viewCount}
                  </RightInfoBox>
                </WritedTitleBox>
                <WritedInfoBox>
                  <LeftWirterBox>작성자</LeftWirterBox>
                  <RightWirterInfoBox>
                    {oneClubData.nickname}
                  </RightWirterInfoBox>
                </WritedInfoBox>
              </BoardTitleBox>
            </BoardTitleSect>

            <BasicSizeContentBox>
              {!editingState ? (
                <COneContTextBox>
                  {oneClubData.content}
                  <br />
                  <br />
                  <br />
                  <br />
                  <br />
                  {oneClubData.content}
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
                  {oneClubData.content}
                  <br />
                  <LikeBtn>좋아요 버튼</LikeBtn>
                </COneContTextBox>
              ) : (
                <EditingCOneContTextBox
                  value={editingContent}
                  onChange={EditingContentFunc}
                />
              )}
            </BasicSizeContentBox>

            {editingState ? (
              <PrevBoardBtn onClick={EditingSubmitFunc}>
                수정 완료하기
              </PrevBoardBtn>
            ) : null}

            <BoardOneCommentSect>
              {baseCommentData.map((data, index) => (
                <>
                  <BoardOneCommentOne key={data.commentId}>
                    <CommentOneLeft>{data.writer}</CommentOneLeft>
                    <CommentOneCenter>{data.content}</CommentOneCenter>
                    <CommentOneRight>{data.createdAt}</CommentOneRight>
                    <SubCommentPlus
                      onClick={() => toggleSubCommentInput(index)}
                    >
                      +
                    </SubCommentPlus>
                  </BoardOneCommentOne>

                  {data.subComment.map((item) => (
                    <>
                      <SubComment key={item}> ============{item}</SubComment>
                    </>
                  ))}
                  {commentInputStates[index].subcommentAdd && (
                    <>
                      <SubcommentInput
                        type="text"
                        name="subcommentText"
                        value={commentInputStates[index].subcommentText}
                        onChange={(event) =>
                          handleSubCommentChange(index, event)
                        }
                      />
                      <button onClick={() => addSubComment(index)}>추가</button>
                    </>
                  )}
                </>
              ))}
            </BoardOneCommentSect>

            <CommentWriteBoxSect>
              <CommentUpSect>
                <CommentUpLeft>
                  <CommentUserBox>작성자</CommentUserBox>
                </CommentUpLeft>
                <CommentUpRight>
                  <CommentRightInputBox
                    value={insertComment}
                    onChange={InsertCommentFunc}
                  />
                </CommentUpRight>
              </CommentUpSect>

              <CommentBottomSect>
                <CommentBottomBtn onClick={CommentChangeBtnClick}>
                  등록
                </CommentBottomBtn>
              </CommentBottomSect>
            </CommentWriteBoxSect>
          </BoardBottomSect>
        </BoardOneContentSect>
      </BoardOneContContainer>
      <OneBoardFooterSect />
    </>
  );
};
