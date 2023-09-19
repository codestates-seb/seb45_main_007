import { styled } from "styled-components";
export const SignUpBox = styled.div`
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .top {
    display: flex;
    flex-direction: column;
    row-gap: 30px;
  }
  .title {
    color: #000;
    font-family: Inter;
    font-size: 32px;
    font-style: normal;
    font-weight: 200;
    line-height: normal;
    display: flex;
    justify-content: center;
  }
  .input-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    height: 450px;
  }
  .input-content {
    display: flex;
    width: 420px;
    height: 44px;
    padding: 10px;
    align-items: center;
    gap: 19px;
    flex-shrink: 0;
    border-radius: 6px;
    border: 1px solid #d9d9d9;
    background: #fff;
  }
  input {
    width: 355px;
    height: 35px;
    border: none;
    background: unset;
    color: #000;
    font-family: Inter;
    font-size: 16px;
    font-style: normal;
    font-weight: 400;
    line-height: normal;
  }
  input:focus {
    border: 0;
    outline: none;
  }
  .error-box {
    width: 420px;
    height: 30px;
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
  .duplicate {
    display: flex;
    width: 74px;
    height: 32px;
    justify-content: center;
    align-items: center;
    flex-shrink: 0;
    border-radius: 6px;
    background: #64c7ff;
    border: 0.5px solid gray;
    font-family: Poppins;
  }
  .signup-button {
    display: flex;
    width: 420px;
    height: 50px;
    justify-content: center;
    align-items: center;
    flex-shrink: 0;
    border-radius: 6px;
    background: #64c7ff;
    color: #fff;
    font-size: 24px;
    font-weight: 700;
    font-family: Poppins;
    border: 1px solid white;
  }
`;
