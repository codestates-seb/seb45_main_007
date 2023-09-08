import { styled } from "styled-components";
export const LoginBox = styled.div`
  box-sizing: border-box;
  width: 420px;
  height: 600px;
  flex-shrink: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .top {
    display: flex;
    flex-direction: column;
    row-gap: 50px;
  }
  .title {
    color: #000;
    font-family: Inter;
    font-size: 32px;
    font-style: normal;
    font-weight: 200;
    line-height: normal;
  }
  .input {
    display: flex;
    width: 100%;
    height: 151px;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    gap: 13px;
    flex-shrink: 0;
  }
  .input-container {
    display: flex;
    width: 100%;
    height: 88px;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    flex-shrink: 0;
  }
  .input-content {
    display: flex;
    width: 100%;
    height: 44px;
    align-items: center;
    flex-shrink: 0;
    border-radius: 6px;
    border: 1px solid #d9d9d9;
    background: #fff;
    font-weight: 400;
    font-size: 16px;
    color: #d9d9d9;
  }
  input {
    width: 355px;
    height: 35px;
    border: none;
    background: unset;
    color: #000;
    font-family: Inter;
    font-size: 22px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
  }
  input:focus {
    border: 0;
    outline: none;
  }
  .LoginButton {
    display: flex;
    width: 420px;
    height: 50px;
    justify-content: center;
    align-items: center;
    border-radius: 6px;
    background: #64c7ff;
    color: #fff;
    font-family: Poppins;
    font-size: 24px;
    font-style: normal;
    font-weight: 700;
    line-height: normal;
  }
  img {
    width: 24px;
    height: 24px;
  }
  .error-message {
    color: #f00;
    font-family: Inter;
    font-size: 14px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
    text-align: center;
    margin-top: 5px;
  }
  .social-box {
    width: 98%;
    height: 94px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 45px;
  }
  .button1 {
    width: 415px;
    height: 44px;
    background-color: white;
    color: black;
    font-size: 18px;
    margin-top: 5px;
    border: 1px solid gray;
    border-radius: 6px;
  }
  .signup-box {
    width: 100%;
    height: 89px;
    margin-top: 30px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
  .signup1 {
    width: 100%;
    height: 50%;
    font-weight: 700;
  }
  .signup2 {
    width: 100%;
    height: 50%;
  }
  .signup-button {
    border: 1px solid gray;
    width: 100%;
    height: 100%;
    background: #fff;
    border-radius: 6px;
  }
`;
