import { useEffect, useState } from "react";
import { fetchProfile } from "../api/profile";
import CohortVariantsPanel from "../components/results/CohortVariantsPanel";
import ParticipantMatchedVariantsPanel from "../components/results/ParticipantMatchedVariantsPanel";
import PhenotypeFilterPanel from "../components/results/PhenotypeFilterPanel";
import SearchDrawer from "../components/results/SearchDrawer";
import TopBar from "../components/results/TopBar";
import {
  ageBreakdown,
  ancestryBreakdown,
  cohortVariantRows,
  filteredVariantRows,
  phenotypeCrosswalk,
  searchSummary,
} from "../data/mockResultsData";
import styles from "./SearchResultsPage.module.css";

export default function SearchResultsPage() {
  const [userEmail, setUserEmail] = useState("");
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [drawerVariants, setDrawerVariants] = useState(searchSummary.variantsRaw);
  const [drawerHpo, setDrawerHpo] = useState(searchSummary.hpoTerm);

  useEffect(() => {
    fetchProfile()
      .then((profile) => setUserEmail(profile.userEmail))
      .catch((err: Error) => console.error("Failed to load profile", err));
  }, []);

  function handleCancelDrawer() {
    setDrawerVariants(searchSummary.variantsRaw);
    setDrawerHpo(searchSummary.hpoTerm);
    setDrawerOpen(false);
  }

  function handleRerunSearch() {
    // TODO: wire up to a real re-query once the backend search endpoint exists.
    console.log("Re-run search submitted", {
      variants: drawerVariants
        .split("\n")
        .map((line) => line.trim())
        .filter(Boolean),
      hpoTerm: drawerHpo.trim(),
    });
    setDrawerOpen(false);
  }

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
          <CohortVariantsPanel rows={cohortVariantRows} />
          <PhenotypeFilterPanel
            crosswalk={phenotypeCrosswalk}
            ancestryBreakdown={ancestryBreakdown}
            ageBreakdown={ageBreakdown}
          />
        </div>

        <ParticipantMatchedVariantsPanel
          rows={filteredVariantRows}
          participantCount={phenotypeCrosswalk.participantCount}
        />
      </main>
    </>
  );
}
