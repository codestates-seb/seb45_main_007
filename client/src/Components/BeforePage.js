import { useNavigate } from "react-router-dom";
import { styled } from "styled-components";
const Button = styled.div`
  width: 43px;
  height: 43px;
  flex-shrink: 0;
  border: 2px solid #3f2305;
  background-color: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const BeforePage = () => {
  const navigate = useNavigate();

  const goBack = () => {
    navigate(-1); //이전 페이지로 이동
  };
  return (
    <Button onClick={goBack}>
      <img src={"/images/BeforePageImage.png"} alt="뒤로가기 버튼"></img>
    </Button>
  );
};

export default BeforePage;
