import styled from "styled-components";
import React, { useEffect, useState } from "react";
import arrowIcon from "../icon/arrow-right.png";
import axios from "axios";
import IsSameDay from "../utility/IsSameDay.jsx";
export default function Reply() {
  const [replyData, setReplyData] = useState([]);
  const [reply, setReply] = useState("");
  const [editedReply, setEditedReply] = useState("");
  const [editingId, setEditingId] = useState(null);

  const handleEditClick = (commentId) => {
    setEditingId(commentId);
  };
  const fetchData = async () => {
    try {
      const response = await axios.get(
        "https://49c9-221-150-55-48.ngrok-free.app/marketBoards/13/comments",
        {
          headers: {
            "Content-Type": `application/json`,
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      console.log(response);
      setReplyData(response.data);
    } catch (error) {
      console.error("Error fetching the data", error);
    }
  };
  useEffect(() => {
    fetchData();
  }, []);

  const submitReply = async () => {
    try {
      const response = await axios.post(
        "https://49c9-221-150-55-48.ngrok-free.app/marketBoards/13/comments",
        { memberId: 1, marketBoardId: 5, content: reply },
        {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      if (response.success) {
        setReply("");
        fetchData();
      }
    } catch (error) {
      console.error("Error posting the reply", error);
    }
  };
  const editReply = async (commentId) => {
    try {
      const response = await axios.put(
        `https://49c9-221-150-55-48.ngrok-free.app/marketBoards/13/comments/${commentId}`,
        { content: editedReply },
        {
          headers: {
            "Content-Type": "application/json",
            "ngrok-skip-browser-warning": "69420",
          },
        },
      );
      if (response.success) {
        setReply("");
        fetchData();
      }
    } catch (error) {
      console.error("Error posting the reply", error);
    }
  };

  const deleteRply = async (commentId) => {
    if (window.confirm("정말로 이 댓글을 삭제하시겠습니까?")) {
      try {
        const response = await axios.delete(
          `https://49c9-221-150-55-48.ngrok-free.app/marketBoards/13/comments/${commentId}`,
          { headers: { "Content-Type": "application/json" } },
        );
        if (response.success) {
          fetchData();
        }
      } catch (error) {
        console.error("Error deleting the post", error);
      }
    }
  };
  return (
    <>
      {replyData.map((data) => {
        const isEditing = data.marketBoardCommentId === editingId;
        return (
          <ReplyContainer key={data.marketBoardCommentId}>
            <ReplyIcon>
              <img src={arrowIcon} alt="icon" />
            </ReplyIcon>
            <ReplyWriterConatiner>{data.nickname} : </ReplyWriterConatiner>
            <ContentContainer>
              {isEditing ? (
                <ReplyContents>
                  <input
                    type="text"
                    placeholder={data.content}
                    onChange={(e) => {
                      setEditedReply(e.target.value);
                    }}
                  />
                  <Button
                    onClick={() => handleEditClick(null)}
                    className="edit"
                  >
                    취소
                  </Button>
                  <Button
                    onClick={() => editReply(data.marketBoardCommentId)}
                    className="edit"
                  >
                    수정
                  </Button>
                </ReplyContents>
              ) : (
                <ReplyContents>{data.content}</ReplyContents>
              )}
              <Bottom>
                {" "}
                {!isEditing && (
                  <Utill
                    onClick={() => handleEditClick(data.marketBoardCommentId)}
                  >
                    수정
                  </Utill>
                )}
                <Utill onClick={() => deleteRply(data.marketBoardCommentId)}>
                  삭제
                </Utill>
                <Utill>답글달기</Utill>
                <ReplyCreatedAt>{IsSameDay(data.createdAt)}</ReplyCreatedAt>
              </Bottom>
            </ContentContainer>
          </ReplyContainer>
        );
      })}
      <ReplyInput
        type="text"
        placeholder="댓글입력"
        onChange={(e) => {
          setReply(e.target.value);
        }}
      ></ReplyInput>
      <ButtonContainer>
        <Button onClick={submitReply}>확인</Button>
      </ButtonContainer>
    </>
  );
}
const ReplyContainer = styled.div`
  width: 75%;
  color: white;
  font-family: "HakgyoansimBunpilR";
  font-size: 30px;
  font-weight: bold;
  border-bottom: 2px solid white;
  display: flex;
  flex-direction: row;
  margin-top: 30px;
`;
const ReplyWriterConatiner = styled.div`
  display: flex;
  margin-left: 20px;
  width: 7%;
  font-size: 20px;
  padding-top: 20px;
`;

const ReplyContents = styled.div`
  width: 100%;
  margin: 20px 0;
  font-size: 20px;
  display: flex;
  flex-direction: row;
  input {
    font-size: 20px;
    width: 70%;
  }
`;
const ReplyCreatedAt = styled.div`
  font-family: "HakgyoansimBunpilR";
  color: white;
  font-size: 16px;
  display: flex;
  align-items: end;
  padding-bottom: 20px;
`;
const ReplyIcon = styled.div`
  img {
    width: 45px;
    height: 45px;
  }
`;
const ReplyInput = styled.textarea`
  width: 75%;
  height: 200px;
  background: none;
  font-size: 26px;
  color: white;
  font-family: "HakgyoansimBunpilR";
  border: 2px solid white;
  margin-top: 30px;
  overflow-y: auto;
  border-radius: 8px;

  &:focus {
    outline: none;
  }
`;
const Button = styled.button`
  display: flex;
  width: 155px;
  height: 48px;
  justify-content: center;
  align-items: center;
  border-radius: 8px;
  background: none;
  color: #fff;
  font-size: 20px;
  border: 2px solid white;
  font-family: "HakgyoansimBunpilR";
  font-weight: bold;
  &:hover {
    cursor: pointer;
  }
  &.edit {
    width: 100px;
    height: 30px;
    font-size: 16px;
  }
`;
const ButtonContainer = styled.div`
  display: flex;
  width: 75%;
  justify-content: end;
  margin: 30px 0;
`;
const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;
const Bottom = styled.div`
  display: flex;
  width: 100%;
  justify-content: flex-end;
  font-size: 16px;
`;
const Utill = styled.div`
  margin-right: 20px;
  color: #756e6e;
  &:hover {
    cursor: pointer;
  }
`;
