import { Route, Routes } from "react-router-dom";
import ScrollToTop from "./components/ScrollToTop";
import { ColorblindModeProvider } from "./context/ColorblindModeContext";
import SearchEntryPage from "./pages/SearchEntryPage";
import SearchResultsPage from "./pages/SearchResultsPage";

function App() {
  return (
    <ColorblindModeProvider>
      <ScrollToTop />
      <Routes>
        <Route path="/" element={<SearchEntryPage />} />
        <Route path="/results" element={<SearchResultsPage />} />
      </Routes>
    </ColorblindModeProvider>
  );
}

export default App;
