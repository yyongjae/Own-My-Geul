import './App.css';
import Home from "./Home";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
function App() {
  return (
    <Router>
      <Routes>
        <Route path='/main' element={<MainPage />} />
        <Route path='/' element={<Home />} />
      </Routes>
    </Router>
  );
}

function MainPage() {
  return <h1>Main Page</h1>
}

export default App;
