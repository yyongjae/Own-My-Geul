import Home from "./pages/Home";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Main from "./pages/Main";
import Result from "./pages/Result"
import Join from "./pages/Join";
function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/main' element={<Main />} />
        <Route path='/result' element={<Result />} />
        <Route path='/join' element={<Join />} />
      </Routes>
    </Router>
  );
}

export default App;