import { useEffect, useState } from "react";
import { fetchProfile } from "../api/profile";
import { fetchSearchResults, type SearchResults } from "../api/searchResults";
import CohortVariantsPanel from "../components/results/CohortVariantsPanel";
import ParticipantMatchedVariantsPanel from "../components/results/ParticipantMatchedVariantsPanel";
import PhenotypeFilterPanel from "../components/results/PhenotypeFilterPanel";
import SearchDrawer from "../components/results/SearchDrawer";
import TopBar from "../components/results/TopBar";
import styles from "./SearchResultsPage.module.css";

export default function SearchResultsPage() {
  const [userEmail, setUserEmail] = useState("");
  const [results, setResults] = useState<SearchResults | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [drawerVariants, setDrawerVariants] = useState("");
  const [drawerHpo, setDrawerHpo] = useState("");

  useEffect(() => {
    fetchProfile()
      .then((profile) => setUserEmail(profile.userEmail))
      .catch((err: Error) => console.error("Failed to load profile", err));
  }, []);

  useEffect(() => {
    fetchSearchResults()
      .then((data) => {
        setResults(data);
        setDrawerVariants(data.searchSummary.variantsRaw);
        setDrawerHpo(data.searchSummary.hpoTerm);
      })
      .catch((err: Error) => setError(err.message));
  }, []);

  function handleCancelDrawer() {
    if (results) {
      setDrawerVariants(results.searchSummary.variantsRaw);
      setDrawerHpo(results.searchSummary.hpoTerm);
    }
    setDrawerOpen(false);
  }

  function handleRerunSearch() {
    // TODO: wire up to a real re-query once the backend search endpoint accepts search terms.
    console.log("Re-run search submitted", {
      variants: drawerVariants
        .split("\n")
        .map((line) => line.trim())
        .filter(Boolean),
      hpoTerm: drawerHpo.trim(),
    });
    setDrawerOpen(false);
  }

  if (error) {
    return <p className={styles.status}>Failed to load search results: {error}</p>;
  }

  if (!results) {
    return <p className={styles.status}>Loading search results…</p>;
  }

  const { searchSummary, phenotypeCrosswalk, ancestryBreakdown, ageBreakdown, cohortVariants, filteredVariants } =
    results;

  return (
    <>
      <TopBar
        variantsEnteredCount={searchSummary.variantsEnteredCount}
        hpoTerm={searchSummary.hpoTerm}
        userEmail={userEmail}
        onModifySearch={() => setDrawerOpen((open) => !open)}
      />

      <SearchDrawer
        open={drawerOpen}
        variantsText={drawerVariants}
        hpoText={drawerHpo}
        variantsLimit={searchSummary.variantsLimit}
        onVariantsChange={setDrawerVariants}
        onHpoChange={setDrawerHpo}
        onCancel={handleCancelDrawer}
        onSearch={handleRerunSearch}
      />

      <main className={styles.main}>
        <div className={styles.topRow}>
          <CohortVariantsPanel rows={cohortVariants} />
          <PhenotypeFilterPanel
            crosswalk={phenotypeCrosswalk}
            ancestryBreakdown={ancestryBreakdown}
            ageBreakdown={ageBreakdown}
          />
        </div>

        <ParticipantMatchedVariantsPanel rows={filteredVariants} participantCount={phenotypeCrosswalk.participantCount} />
      </main>
    </>
  );
}
