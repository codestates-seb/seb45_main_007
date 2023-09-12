import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./Pages/Login";
import SignUp from "./Pages/SignUp";
import Club from "./Pages/Club";
import Header from "./Components/Header";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <div className="App">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/club" element={<Club />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}
export default App;
