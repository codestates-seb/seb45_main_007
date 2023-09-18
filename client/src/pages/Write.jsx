import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { styled } from "styled-components";
import axios from "axios";

const WritePage = styled.div`
  margin-top: 100px;
  display: flex;
  justify-content: center;
`;

const PageBox = styled.div`
  width: 1440px;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  gap: 3px;
`;

const Title = styled.div`
  width: 75%;
  height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-size: 32px;
  align-items: center;
  margin-bottom: 30px;
`;

const SelectBox = styled.div`
  width: 75%;
`;

const TitleBox = styled.input`
  width: 75%;
  height: 50px;
  padding: 10px;
  font-size: 22px;
`;
const ImageBox = styled.div`
  width: 75%;
  display: flex;
  justify-content: start;
`;

const ContentBox = styled.textarea`
  width: 75%;
  height: 750px;
  padding: 10px;
`;

const ButtonBox = styled.div`
  width: 75%;
  display: flex;
  justify-content: center;
`;

const SubmitButton = styled.button`
  width: 155px;
  height: 48px;
`;

const Write = () => {
  const [board, setBoard] = useState("");
  const [category, setCategory] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [photo, setPhoto] = useState(null);

  const navigate = useNavigate();

  const handleValidation = () => {
    if (
      board &&
      title &&
      content &&
      (board !== "동아리" || (board === "동아리" && category))
    ) {
      return true;
    }
    return false;
  };

  const handlePhotoUpload = (e) => {
    setPhoto(e.target.files[0]);
  };

  const handleCancel = () => {
    navigate(-1);
  };

  const handlePostSubmit = async () => {
    if (handleValidation()) {
      let apiUrl = "";
      let payload = {};

      if (board === "바자회") {
        apiUrl = "https://49c9-221-150-55-48.ngrok-free.app/marketBoards";
        payload = {
          memberId: 1,
          title: title,
          content: content,
          photo: photo ? photo.name : "", // photo가 파일 객체를 가지고 있다고 가정
          priceContent: 10000, // 필요하다면 동적 값 추가
          tag: "SALE", // 필요하다면 동적 값 추가
        };
      } else if (board === "동아리") {
        apiUrl = "https://YOUR_DOMAIN_HERE/clubBoards"; // 실제 도메인으로 업데이트
        payload = {
          memberId: 1,
          title: title,
          content: content,
          photo: photo ? photo.name : "", // photo가 파일 객체를 가지고 있다고 가정
          voice: "", // 이 값은 선택사항이며 비워 둘 수 있다고 가정. 필요하다면 동적 값 추가
          category: category,
        };
      }

      const formData = new FormData();
      for (let key in payload) {
        formData.append(key, payload[key]);
      }

      if (photo) {
        formData.append("photo", photo);
      }

      const config = {
        headers: {
          "Content-type": "multipart/form-data",
          Authorization: `Bearer [YOUR_DYNAMIC_JWT_TOKEN_HERE]`, // 동적 토큰 검색 방법으로 업데이트
        },
      };

      try {
        const response = await axios.post(apiUrl, formData, config);
        if (response.data.success) {
          console.log("글이 성공적으로 등록되었습니다.");
          if (board === "바자회") {
            navigate("/market/onecontent");
          } else if (board === "동아리") {
            navigate("/club/onecontent");
          }
        } else {
          console.error("글 등록에 실패하였습니다.");
        }
      } catch (error) {
        console.error("Error", error);
      }
    } else {
      alert("모든 필드를 올바르게 입력해주세요.");
    }
  };
  return (
    <WritePage>
      <PageBox>
        <Title>글 작성 페이지</Title>
        <SelectBox>
          <select onChange={(e) => setBoard(e.target.value)} value={board}>
            <option value="" disabled>
              게시판 선택
            </option>
            <option value="바자회">바자회</option>
            <option value="동아리">동아리</option>
          </select>

          {board === "동아리" && (
            <select
              onChange={(e) => setCategory(e.target.value)}
              value={category}
            >
              <option value="" disabled>
                카테고리 선택
              </option>
              <option value="만화">만화</option>
              <option value="영화">영화</option>
              <option value="TV프로그램">TV프로그램</option>
              <option value="추억템">추억템</option>
              <option value="노래">노래</option>
              <option value="게임">게임</option>
              <option value="기타">기타</option>
            </select>
          )}
        </SelectBox>

        <TitleBox
          type="text"
          placeholder="제목"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <ImageBox>
          <input type="file" onChange={handlePhotoUpload} />
        </ImageBox>
        <ContentBox
          placeholder="내용"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <ButtonBox>
          <SubmitButton onClick={handlePostSubmit}>게시글 등록</SubmitButton>
          <SubmitButton onClick={handleCancel}>취소</SubmitButton>
        </ButtonBox>
      </PageBox>
    </WritePage>
  );
};

export default Write;