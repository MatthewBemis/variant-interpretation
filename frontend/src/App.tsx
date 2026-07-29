import { Route, Routes } from "react-router-dom";
import SearchEntryPage from "./pages/SearchEntryPage";
import SearchResultsPage from "./pages/SearchResultsPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<SearchEntryPage />} />
      <Route path="/results" element={<SearchResultsPage />} />
    </Routes>
  );
}

export default App;
