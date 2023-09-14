import styled from "styled-components";
import React, { useEffect, useState } from "react";
import arrowIcon from "../icon/arrow-right.png";
import axios from "axios";
export default function Reply() {
  const [replyData, setReplyData] = useState([]);
  const [reply, setReply] = useState("");

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "https://49c9-221-150-55-48.ngrok-free.app/marketBoards/12/comments",
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
        "https://49c9-221-150-55-48.ngrok-free.app/marketBoards/25/comments",
        { content: reply },
        { headers: { "Content-Type": "application/json" } },
      );
      if (response.status === 200) {
        setReply("");
        fetchData();
      }
    } catch (error) {
      console.error("Error posting the reply", error);
    }
  };
  return (
    <>
      {replyData.map((data) => {
        return (
          <ReplyContainer key={data.marketBoardCommentId}>
            <ReplyIcon>
              <img src={arrowIcon} alt="icon" />
            </ReplyIcon>
            <ReplyWriterConatiner>{data.nickname} : </ReplyWriterConatiner>
            <ReplyContents>{data.content}</ReplyContents>
            <ReplyCreatedAt>{data.createdAt}</ReplyCreatedAt>
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
`;
const ButtonContainer = styled.div`
  display: flex;
  width: 75%;
  justify-content: end;
  margin: 30px 0;
`;
