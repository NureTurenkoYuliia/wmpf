import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "../src/components/Home";
import Profile from "../src/components/Profile";

function App() {

  return (
     <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/profile/:id" element={<Profile />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
