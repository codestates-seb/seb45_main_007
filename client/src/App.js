import "./App.css";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import { SchoolTheme } from "./pages/SchoolTheme.jsx";
import { Board } from "./pages/Board.jsx";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<SchoolTheme />} />
        <Route path="/SchoolHome" element={<SchoolTheme />} />
        <Route path="/board" element={<Board />} />
      </Routes>
    </Router>
  );
}
export default App;
