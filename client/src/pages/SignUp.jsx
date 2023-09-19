import { SignUpBox } from "./styles/SignUpBox";
import { useState, React } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { styled } from "styled-components";

const PageStyle = styled.div`
  padding: 65px 0px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  column-gap: 20px;
`;

export default function SignUp() {
  const [email, setId] = useState("");
  const [idIsValid, setIdIsValid] = useState(false);
  const [idTouched, setIdTouched] = useState(false);

  const [password, setPassword] = useState("");
  const [passwordIsValid, setPasswordIsValid] = useState(false);
  const [passwordTouched, setPasswordTouched] = useState(false);

  const [password2, setPassword2] = useState("");
  const [password2IsValid, setPassword2IsValid] = useState(false);
  const [password2Touched, setPassword2Touched] = useState(false);

  const [username, setName] = useState("");
  const [nameIsValid, setNameIsValid] = useState(false);
  const [nameTouched, setNameTouched] = useState(false);

  const [nickname, setNickname] = useState("");

  const navigate = useNavigate();

  const onChangeHandlerId = (e) => {
    setIdTouched(true);
    setId(e.target.value);
    if (e.target.value.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
      setIdIsValid(true);
    } else {
      setIdIsValid(false);
    }
  };
  const onChangeHandlerName = (e) => {
    setNameTouched(true);
    setName(e.target.value);
    if (e.target.value.length < 1) {
      setNameIsValid(false);
    } else {
      setNameIsValid(true);
    }
  };
  const onChangeHandlerPassword = (e) => {
    setPasswordTouched(true);
    setPassword(e.target.value);
    if (e.target.value.length >= 8 && e.target.value.length <= 16) {
      setPasswordIsValid(true);
    } else {
      setPasswordIsValid(false);
    }
  };
  const onChangeHandlerPassword2 = (e) => {
    setPassword2Touched(true);
    setPassword2(e.target.value);
    if (e.target.value !== password) {
      setPassword2IsValid(false);
    } else {
      setPassword2IsValid(true);
    }
  };

  const signUp = async () => {
    if (idIsValid && passwordIsValid && password2IsValid && nameIsValid) {
      try {
        const response = await axios.post(
          "https://e5da-2406-5900-705c-f80b-14a4-7259-d8f4-2a43.ngrok-free.app/members/signup",
          {
            email,
            password,
            username,
            nickname,
          },
          {
            headers: {
              "Content-Type": "application/json",
            },
          },
        );
        console.log(response.data.message);
        if (response.data.success) {
          alert("회원가입에 성공하셨습니다.");
          navigate(-1);
        }
      } catch (error) {
        console.log("failed to signUp");
        console.error("Request error:", error);
      }
    }
  };

  return (
    <PageStyle>
      <SignUpBox>
        <div className="top">
          <div className="title">회원가입</div>
          <div className="input-container">
            <div className="input-content">
              <img src="/images/ic-outline-email.png" alt=""></img>
              <input
                type="text"
                onChange={onChangeHandlerId}
                value={email}
                placeholder="example@email.com"
              ></input>
            </div>
            <div className="error-box">
              {idTouched && !idIsValid ? (
                <div className="error-message">
                  유효한 이메일을 입력 해주세요.
                </div>
              ) : null}
            </div>
            <div className="input-content">
              <img src="/images/ph-user.png" alt=""></img>
              <input
                type="text"
                onChange={onChangeHandlerName}
                value={username}
                placeholder="김코딩"
              ></input>
            </div>
            <div className="error-box">
              {nameTouched && !nameIsValid ? (
                <div className="error-message">이름을 입력 해주세요</div>
              ) : null}
            </div>
            <div className="input-content">
              <img src="/images/ph-user.png" alt=""></img>
              <input
                type="text"
                onChange={(e) => setNickname(e.target.value)}
                value={nickname}
                placeholder="닉네임을 입력해주세요"
              ></input>
            </div>
            <div className="error-box"></div>
            <div className="input-content">
              <img src="/images/mdi-password-outline.png" alt=""></img>
              <input
                type="password"
                onChange={onChangeHandlerPassword}
                value={password}
                placeholder="비밀번호를 입력해주세요"
              ></input>
            </div>
            <div className="error-box">
              {passwordTouched && !passwordIsValid ? (
                <div className="error-message">
                  8 ~ 16글자의 비밀번호를 입력해주세요.
                </div>
              ) : null}
            </div>
            <div className="input-content">
              <img src="/images/mdi-password-outline.png" alt=""></img>
              <input
                type="password"
                onChange={onChangeHandlerPassword2}
                value={password2}
                placeholder="비밀번호와 동일하게 입력해주세요"
              ></input>
            </div>
            <div className="error-box">
              {password2Touched && !password2IsValid ? (
                <div className="error-message">
                  비밀번호가 일치하지 않습니다.
                </div>
              ) : null}
            </div>
          </div>
          <div>
            <button className="signup-button" onClick={signUp}>
              SignUp
            </button>
          </div>
        </div>
      </SignUpBox>
    </PageStyle>
  );
}
