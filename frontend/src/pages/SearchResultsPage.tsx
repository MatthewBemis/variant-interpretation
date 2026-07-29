import { useState } from "react";
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

const CURRENT_USER_NAME = "Lee Lichtenstein";

export default function SearchResultsPage() {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [drawerVariants, setDrawerVariants] = useState(searchSummary.variantsRaw);
  const [drawerHpo, setDrawerHpo] = useState(searchSummary.hpoTerm);

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
        userName={CURRENT_USER_NAME}
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
