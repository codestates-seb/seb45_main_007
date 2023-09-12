import { styled } from "styled-components";
import { React } from "react";

export default function UserModify() {
  return (
    <>
      <Container>
        <ModifyContainer>
          <Title>비밀번호 변경</Title>
          <InputContainer>
            <Label>현재 비밀번호 </Label>
            <InputContents>
              <Input type="password"></Input>
            </InputContents>
          </InputContainer>
          <InputContainer>
            <Label>변경할 비밀번호 </Label>
            <InputContents>
              <Input type="password"></Input>
            </InputContents>
          </InputContainer>
          <InputContainer>
            <Label>비밀번호 확인</Label>
            <InputContents>
              <Input type="password"></Input>
            </InputContents>
          </InputContainer>
          <ButtonContainer>
            <Button>뒤로가기</Button>
            <Button>확인</Button>
          </ButtonContainer>
        </ModifyContainer>
      </Container>
    </>
  );
}
const Container = styled.div`
  display: flex;
  justify-content: center;
`;
const ModifyContainer = styled.div`
  box-sizing: border-box;
  width: 420px;
  height: 600px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
`;
const Title = styled.div`
  color: #000;
  font-family: Inter;
  font-size: 32px;
  font-style: normal;
  font-weight: 200;
  line-height: normal;
  margin-bottom: 50px;
`;
const Label = styled.div`
  color: #000;
  font-family: Inter;
  font-size: 16px;
  font-style: normal;
  font-weight: 300;
  line-height: normal;
  width: 30%;
  display: flex;
  justify-content: start;
  align-items: center;
  height: 46px;
`;
const InputContainer = styled.div`
  display: flex;
  width: 100%;
  height: 88px;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
  text-align: center;
`;
const InputContents = styled.div`
  display: flex;
  width: 70%;
  height: 44px;
  align-items: center;
  flex-shrink: 0;
  border-radius: 6px;
  border: 1px solid #d9d9d9;
  background: #fff;
  font-weight: 400;
  font-size: 16px;
  color: #d9d9d9;
  .nickname {
    width: 50px;
  }
`;
const Input = styled.input`
  width: 355px;
  height: 35px;
  border: none;
  background: unset;
  color: #000;
  font-family: Inter;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: normal;

  &:focus {
    border: 0;
    outline: none;
  }
`;
const ButtonContainer = styled.div`
  display: flex;
  width: 100%;
  justify-content: space-around;
`;
const Button = styled.button`
  display: flex;
  width: 30%;
  height: 50px;
  justify-content: center;
  align-items: center;
  border-radius: 6px;
  background: #64c7ff;
  color: #fff;
  font-family: Poppins;
  font-size: 20px;
  font-style: normal;
  font-weight: 700;
  line-height: normal;
`;
