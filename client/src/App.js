import "./App.css";
import Mypage from "./pages/Mypage";
import UserModify from "./pages/UserModify";
import BuySellContent from "./pages/BuySellContent";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/mypage" element={<Mypage />} />
          <Route path="/marketOne" element={<BuySellContent />} />
          <Route path="/usermodify" element={<UserModify />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
