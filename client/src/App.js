import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { MarketTContPage } from "./pages/MarketTContPage.jsx";
import { ClubTContPage } from "./pages/ClubTContPage.jsx";
import { ClubOneContPage } from "./pages/ClubOneContPage.jsx";
import Login from "./pages/Login.jsx";
import SignUp from "./pages/SignUp.jsx";
import ClubMainPage from "./pages/ClubMainPage.jsx";
import Mypage from "./pages/Mypage.jsx";
import UserModify from "./pages/UserModify.jsx";
import MarketOneContPage from "./pages/MarketOneContPage.jsx";
import React, { useEffect } from "react";
import { NewHeader } from "./components/NewHeader.jsx";
import Write from "./pages/Write.jsx";
import KakaoAuth from "./pages/KakaoAuth.jsx";
import GoogleOAuth2RedirectPage from "./pages/GoogleOAuth2RedirectPage.jsx";
import { useDispatch } from "react-redux";
import { setUser } from "./redux/userSlice.js";

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    const accessToken = localStorage.getItem("accessToken");
    const refreshToken = localStorage.getItem("refreshToken");
    const email = localStorage.getItem("email");
    const name = localStorage.getItem("name");
    const memberId = localStorage.getItem("memberId");

    if (accessToken && refreshToken) {
      dispatch(
        setUser({
          loggedIn: true,
          email,
          name,
          memberId,
          accessToken,
          refreshToken,
        }),
      );
    }
  }, [dispatch]);
  return (
    <Router>
      <NewHeader></NewHeader>
      <Routes>
        <Route path="/" element={<MarketTContPage />} />
        <Route path="/market/totalcontents" element={<MarketTContPage />} />
        <Route path="/club/:category" element={<ClubTContPage />} />
        <Route
          path="/club/:category/:clubBoardId"
          element={<ClubOneContPage />}
        />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/club" element={<ClubMainPage />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/market/:marketBoardId" element={<MarketOneContPage />} />
        <Route path="/usermodify" element={<UserModify />} />
        <Route path="/write" element={<Write />} />
        <Route path="/oauth2/authorization/kakao" element={<KakaoAuth />} />
        <Route
          path="/oauth2/authorization/google"
          element={<GoogleOAuth2RedirectPage />}
        />
      </Routes>
    </Router>
  );
}
export default App;
