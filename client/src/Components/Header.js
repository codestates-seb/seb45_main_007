import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import { styled } from "styled-components";

const HeaderContainer = styled.div`
  display: flex;
  height: 92px;
  background: #fff;
  border: 3px solid black;
`;
const LogoContainer = styled.div`
  width: 104px;
  height: 52px;
  justify-content: center;
  align-items: center;
  display: flex;
  flex-direction: column;
  border: 3px solid black;
`;
const ButtonContainer = styled.div`
  display: flex;
  width: 800px;
  height: 61px;
  padding: 16px 20px;
  font-size: 20px;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
  border: 3px solid black;
`;
const PageContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 50px;
`;
const Header = () => {
  const navigate = useNavigate();
  const user = useSelector((state) => state.user);

  const Logo = () => {
    navigate("/");
  };
  const market = () => {
    navigate("/market");
  };
  const club = () => {
    navigate("/club");
  };
  const login = () => {
    navigate("/login");
  };

  const mypage = () => {
    navigate("/mypage");
  };

  return (
    <HeaderContainer>
      <LogoContainer onClick={Logo}>
        <div className="title">로고자리</div>
      </LogoContainer>
      <ButtonContainer>
        <PageContainer>
          <div onClick={market} role="presentation">
            바자회
          </div>
          <div onClick={club} role="presentation">
            동아리
          </div>
        </PageContainer>
        {user.loggedIn ? (
          <div onClick={login} role="presentation">
            로그인/가입
          </div>
        ) : (
          <div onClick={mypage} role="presentation">
            마이페이지
          </div>
        )}
      </ButtonContainer>
    </HeaderContainer>
  );
};

export default Header;
