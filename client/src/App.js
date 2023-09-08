import "./App.css";
import Login from "./Pages/Login";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="App">
        <Login />
      </div>
    </Router>
  );
}

export default App;
