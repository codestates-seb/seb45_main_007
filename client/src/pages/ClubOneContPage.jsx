import { styled } from "styled-components";
import { NewHeader } from "../components/NewHeader.jsx";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useParams, useNavigate } from "react-router-dom";

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

const LikeBtn = styled.div`
  width: 50px;
  height: 50px;
  background-color: blue;
`;

const DeleteModal = styled.div`
  width: 500px;
  height: 500px;
  background-color: white;
  color: black;
  cursor: pointer;
  font-size: 32px;
  position: absolute;
  z-index: 100022;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const DeleteCommentBtn = styled.div`
  width: 50px;
  height: 50px;
  background-color: white;
  color: black;
  font-size: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
`;

export const ClubOneContPage = () => {
  const { clubBoardId } = useParams();
  const { category } = useParams();
  const [oneClubData, setOneClubData] = useState([]);
  const OneContApiUrl = `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}`;

  const OneCommentAPiUrl = `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}/comments?page=1&size=5`;
  const [editingState, setEditingState] = useState(false);
  const [editingTitle, setEditingTitle] = useState(oneClubData.title);
  const [editingContent, setEditingContent] = useState(oneClubData.content);
  const [baseCommentData, setBaseCommentData] = useState([]);
  const [insertComment, setInserComment] = useState("");

  const [deleteState, setDeleteState] = useState(false);

  const navigate = useNavigate();

  const DeleteChangeFunc = () => {
    setDeleteState(true);
  };

  const InsertCommentFunc = (event) => {
    const newValue = event.target.value;
    setInserComment(newValue);
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

  // const JWT_TOKEN = "YOUR_JWT_TOKEN";

  const dataToSend = {
    title: editingTitle,
    content: editingContent,
  };

  const EditingSubmitFunc = async () => {
    try {
      const response = await axios.put(
        `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}`,
        dataToSend, // 데이터는 여기에 넣어야 합니다.
        {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      if (response.status === 200) {
        console.log("수정 성공");
        navigate(`club/${category}`);
      } else {
        console.log("수정 실패");
      }
    } catch (error) {
      console.error("수정 요청 실패:", error);
    }
  };

  const dataToSendComment = {
    memberId: 1,
    content: insertComment,
  };

  const CommentChangeBtnClick = async () => {
    try {
      const response = await axios.post(
        `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}/comments`,
        dataToSendComment, // 데이터는 여기에 넣어야 합니다.
        {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      if (response.status === 201) {
        console.log("댓글 추가 성공");
      } else {
        console.log("수정 실패");
      }
    } catch (error) {
      console.error("수정 요청 실패:", error);
    }
  };

  const DeleteCommentBtnClick = async (clubBoardCommentId) => {
    try {
      const response = await axios.delete(
        `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}/comments/${clubBoardCommentId}`,
        {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      if (response.status === 200) {
        console.log("댓글 삭제 성공");
        fetchCommentData();
      } else {
        console.log("수정 실패");
      }
    } catch (error) {
      console.error("수정 요청 실패:", error);
    }
  };

  const fetchCommentData = async () => {
    try {
      const response = await axios.get(OneCommentAPiUrl, {
        headers: {
          "Content-Type": "application/json",
          "ngrok-skip-browser-warning": "69420",
        },
      });

      if (response.status === 200) {
        setBaseCommentData(response.data.clubBoardComments);
      } else {
        console.error("200 코드가 아님");
      }
    } catch (error) {
      console.log("error");
    }
  };

  useEffect(() => {
    fetchCommentData();
  }, []);

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
        console.error("200 코드가 아님");
      }
    }
    fetchData();
  }, []);

  const DeleteContentBtnClick = async () => {
    const response = await axios.delete(
      `https://01db-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/clubBoards/${clubBoardId}`,
      {
        headers: {
          "Content-Type": "application/json",
          "ngrok-skip-browser-warning": "69420",
        },
      },
    );

    if (response.status === 200) {
      navigate(`/club/${category}`);
      setDeleteState(false);
      console.log("삭제됨");
    } else {
      console.log("에러코드 실행됨: 삭제");
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
  return (
    <>
      <NewHeader />
      {deleteState ? (
        <DeleteModal onClick={DeleteContentBtnClick}>
          누르면 삭제 됩니다.
        </DeleteModal>
      ) : null}
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
            <PrevBoardBtn onClick={DeleteChangeFunc}>글 삭제 하기</PrevBoardBtn>
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
                  <img src={oneClubData.photo} alt="fdf" />
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
              {baseCommentData.map((data) => (
                <>
                  <BoardOneCommentOne key={data.clubBoardCommentId}>
                    <CommentOneLeft>{data.nickname}</CommentOneLeft>
                    <CommentOneCenter>{data.content}</CommentOneCenter>
                    <CommentOneRight>{data.createdAt}</CommentOneRight>
                    <DeleteCommentBtn
                      onClick={() =>
                        DeleteCommentBtnClick(data.clubBoardCommentId)
                      }
                    >
                      댓글 삭제하기
                    </DeleteCommentBtn>
                  </BoardOneCommentOne>
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
