import "./App.css";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { MarketTContPage } from "./pages/MarketTContPage.jsx";
import { ClubTContPage } from "./pages/ClubTContPage.jsx";
import { ClubOneContPage } from "./pages/ClubOneContPage.jsx";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<MarketTContPage />} />
        <Route path="/market/totalcontents" element={<MarketTContPage />} />
        <Route path="/club/totalcontents" element={<ClubTContPage />} />
        <Route path="/club/onecontent" element={<ClubOneContPage />} />
      </Routes>
    </Router>
  );
}
export default App;
