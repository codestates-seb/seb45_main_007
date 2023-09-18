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
import React from "react";
import "./App.css";
import { NewHeader } from "./components/NewHeader.jsx";
// import { ClubTestPage } from "./pages/ClubTestPage.jsx";

function App() {
  return (
    <Router>
      <NewHeader />
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
        <Route path="/market/onecontent" element={<MarketOneContPage />} />
        <Route path="/usermodify" element={<UserModify />} />
      </Routes>
    </Router>
  );
}
export default App;
