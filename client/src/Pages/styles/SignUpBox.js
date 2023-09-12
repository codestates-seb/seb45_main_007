import { styled } from "styled-components";
export const SignUpBox = styled.div`
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .title {
    color: #000;
    font-family: Inter;
    font-size: 32px;
    font-style: normal;
    font-weight: 200;
    line-height: normal;
  }
  .input-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 300px;
    border: 3px solid black;
  }
  .input-content {
    width: 420px;
    height: 44px;
    display: flex;
    justify-content: center;
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
  .signup-button {
    border: 1px solid gray;
    width: 100%;
    height: 100%;
    background: #fff;
    border-radius: 6px;
  }
`;
