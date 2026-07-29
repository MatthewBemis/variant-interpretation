package org.broadinstitute.variantinterpretation;

import java.math.BigDecimal;
import java.util.List;
import org.broadinstitute.variantinterpretation.api.SearchResultsApi;
import org.broadinstitute.variantinterpretation.model.BreakdownSegment;
import org.broadinstitute.variantinterpretation.model.CohortVariant;
import org.broadinstitute.variantinterpretation.model.FilteredVariant;
import org.broadinstitute.variantinterpretation.model.PhenotypeCrosswalk;
import org.broadinstitute.variantinterpretation.model.SearchResultsResponse;
import org.broadinstitute.variantinterpretation.model.SearchSummary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchResultsController implements SearchResultsApi {

  @Override
  public ResponseEntity<SearchResultsResponse> searchResults() {
    return ResponseEntity.ok(
        new SearchResultsResponse()
            .searchSummary(searchSummary())
            .phenotypeCrosswalk(phenotypeCrosswalk())
            .ancestryBreakdown(ancestryBreakdown())
            .ageBreakdown(ageBreakdown())
            .cohortVariants(cohortVariants())
            .filteredVariants(filteredVariants()));
  }

  private static SearchSummary searchSummary() {
    return new SearchSummary()
        .variantsRaw(
            """
            8-11708582-C-T
            8-11708590-G-GAA
            8-11708598-T-C
            8-11708605-A-G
            8-11708613-C-T
            8-11708621-G-T
            8-11708629-T-A
            8-11708637-A-C
            8-11708645-CGGGG-C
            8-11708653-A-G
            8-11708661-C-T
            8-11708669-G-A
            8-11708677-T-C
            8-11708685-A-T
            8-11708693-C-G
            8-11708701-G-T
            8-11708709-A-C
            8-11708717-T-G
            8-11708725-C-A
            8-11708733-G-C
            8-11708741-A-G"""
                .stripIndent()
                .stripTrailing())
        .variantsEnteredCount(21)
        .variantsLimit(50)
        .hpoTerm("HP:0001636");
  }

  private static PhenotypeCrosswalk phenotypeCrosswalk() {
    return new PhenotypeCrosswalk()
        .hpoCode("HP:0001636")
        .omopCode("313867")
        .description("Tetralogy of Fallot")
        .participantCount(214);
  }

  private static List<BreakdownSegment> ancestryBreakdown() {
    return List.of(
        segment("EUR", 48.3, "#F9C854"),
        segment("AFR", 19.7, "#2078B4"),
        segment("AMR", 17.8, "#6DACE4"),
        segment("OTH", 9, "#B3AEAD"),
        segment("EAS", 3, "#A27BD7"),
        segment("SAS", 2, "#8CCA90"),
        segment("MID", 0.2, "#CB2D4C"));
  }

  private static List<BreakdownSegment> ageBreakdown() {
    return List.of(
        segment("18–29", 7.9, "#B8DCEF"),
        segment("30–39", 14.0, "#8DC6E5"),
        segment("40–49", 21.5, "#5FAEDA"),
        segment("50–59", 26.2, "#3B8FC4"),
        segment("60–69", 21.9, "#2569A0"),
        segment("70+", 8.5, "#17456F"));
  }

  private static BreakdownSegment segment(String label, double percent, String color) {
    return new BreakdownSegment().label(label).percent(BigDecimal.valueOf(percent)).color(color);
  }

  private static List<CohortVariant> cohortVariants() {
    return List.of(
        annotatedVariant(
            "8-11708582-C-T", "Missense", CohortVariant.SubpopulationEnum.EUR, 0.0034, 1735, 517466, 0.004, 612,
            152312, CohortVariant.ClinvarSignificanceEnum.VUS, 0.09, null),
        annotatedVariant(
            "8-11708590-G-GAA", "Frameshift", CohortVariant.SubpopulationEnum.AFR, 0.0018, 388, 211058, 0.0006, 89,
            152312, CohortVariant.ClinvarSignificanceEnum.PATHOGENIC, 0.07, CohortVariant.PlofEnum.HC),
        annotatedVariant(
            "8-11708598-T-C", "Synonymous", CohortVariant.SubpopulationEnum.EUR, 0.05, 25895, 517466, 0.0489, 7452,
            152312, CohortVariant.ClinvarSignificanceEnum.BENIGN, 0.02, null),
        annotatedVariant(
            "8-11708605-A-G", "Missense", CohortVariant.SubpopulationEnum.AMR, 0.0062, 1190, 190702, 0.0013, 203,
            152312, CohortVariant.ClinvarSignificanceEnum.VUS, 0.04, null),
        annotatedVariant(
            "8-11708613-C-T", "Nonsense", CohortVariant.SubpopulationEnum.EUR, 0.0002, 127, 517466, 0.0001, 22,
            152312, CohortVariant.ClinvarSignificanceEnum.PATHOGENIC, 0.02, CohortVariant.PlofEnum.HC),
        annotatedVariant(
            "8-11708621-G-T", "Splice site", CohortVariant.SubpopulationEnum.EAS, 0.004, 128, 32140, 0.0004, 58,
            152312, CohortVariant.ClinvarSignificanceEnum.VUS, 0.77, CohortVariant.PlofEnum.HC),
        annotatedVariant(
            "8-11708629-T-A", "Missense", CohortVariant.SubpopulationEnum.SAS, 0.005, 107, 21428, 0.0002, 31, 152312,
            CohortVariant.ClinvarSignificanceEnum.VUS, 0.08, null),
        annotatedVariant(
            "8-11708637-A-C", "Missense", CohortVariant.SubpopulationEnum.EUR, 0.0733, 37954, 517466, 0.0669, 10190,
            152312, CohortVariant.ClinvarSignificanceEnum.BENIGN, 0.01, null),
        annotatedVariant(
            "8-11708645-CGGGG-C", "Frameshift", CohortVariant.SubpopulationEnum.OTH, 0.005, 486, 96422, 0.0001, 9,
            152312, CohortVariant.ClinvarSignificanceEnum.PATHOGENIC, 0.08, CohortVariant.PlofEnum.HC),
        annotatedVariant(
            "8-11708653-A-G", "Nonsense", CohortVariant.SubpopulationEnum.EUR, 0.0033, 1707, 517466, 0.0369, 5620,
            152312, CohortVariant.ClinvarSignificanceEnum.BENIGN, 0.02, null),
        unannotatedVariant("8-11708661-C-T"),
        unannotatedVariant("8-11708669-G-A"),
        unannotatedVariant("8-11708677-T-C"),
        unannotatedVariant("8-11708685-A-T"),
        unannotatedVariant("8-11708693-C-G"),
        unannotatedVariant("8-11708701-G-T"),
        unannotatedVariant("8-11708709-A-C"),
        unannotatedVariant("8-11708717-T-G"),
        unannotatedVariant("8-11708725-C-A"),
        unannotatedVariant("8-11708733-G-C"),
        unannotatedVariant("8-11708741-A-G"));
  }

  private static CohortVariant annotatedVariant(
      String variant,
      String classification,
      CohortVariant.SubpopulationEnum subpopulation,
      double aouAf,
      int aouAc,
      int aouAn,
      double gnomadAf,
      int gnomadAc,
      int gnomadAn,
      CohortVariant.ClinvarSignificanceEnum clinvarSignificance,
      double spliceAi,
      CohortVariant.PlofEnum plof) {
    return new CohortVariant()
        .variant(variant)
        .gene("GATA4")
        .annotated(true)
        .classification(classification)
        .subpopulation(subpopulation)
        .aouAf(BigDecimal.valueOf(aouAf))
        .aouAc(aouAc)
        .aouAn(aouAn)
        .gnomadAf(BigDecimal.valueOf(gnomadAf))
        .gnomadAc(gnomadAc)
        .gnomadAn(gnomadAn)
        .gnomadUrl("#")
        .clinvarSignificance(clinvarSignificance)
        .clinvarUrl("https://www.ncbi.nlm.nih.gov/clinvar/?term=" + variant)
        .spliceAi(BigDecimal.valueOf(spliceAi))
        .plof(plof);
  }

  private static CohortVariant unannotatedVariant(String variant) {
    return new CohortVariant()
        .variant(variant)
        .gene("GATA4")
        .annotated(false)
        .classification(null)
        .subpopulation(null)
        .aouAf(null)
        .aouAc(null)
        .aouAn(null)
        .gnomadAf(null)
        .gnomadAc(null)
        .gnomadAn(null)
        .gnomadUrl(null)
        .clinvarSignificance(null)
        .clinvarUrl(null)
        .spliceAi(null)
        .plof(null);
  }

  private static List<FilteredVariant> filteredVariants() {
    return List.of(
        filteredVariantWithStats("8-11708582-C-T", "Missense", 1, 428, 0.0023, 0, 1, 0, 0.7),
        filteredVariantWithStats("8-11708590-G-GAA", "Frameshift", 32, 428, 0.0748, 4, 24, 2, 40.7),
        filteredVariantWithStats("8-11708598-T-C", "Synonymous", 21, 428, 0.0491, 1, 19, 0, 1.0),
        filteredVariantWithStats("8-11708605-A-G", "Missense", 3, 428, 0.007, 0, 3, 0, 1.1),
        filteredVariantWithStats("8-11708613-C-T", "Nonsense", 0, 428, 0.0, 0, 0, 0, 0.0),
        filteredVariantWithStats("8-11708621-G-T", "Splice site", 2, 428, 0.0047, 0, 2, 0, 1.2),
        filteredVariantWithStats("8-11708629-T-A", "Missense", 2, 428, 0.0047, 0, 2, 0, 0.9),
        filteredVariantWithStats("8-11708637-A-C", "Missense", 31, 428, 0.0724, 1, 29, 0, 1.0),
        filteredVariantWithStats("8-11708645-CGGGG-C", "Frameshift", 2, 428, 0.0047, 0, 2, 1, 0.9),
        unfilteredVariant("8-11708653-A-G", "Nonsense"),
        unfilteredVariant("8-11708661-C-T", null),
        unfilteredVariant("8-11708669-G-A", null),
        unfilteredVariant("8-11708677-T-C", null),
        unfilteredVariant("8-11708685-A-T", null),
        unfilteredVariant("8-11708693-C-G", null),
        unfilteredVariant("8-11708701-G-T", null),
        unfilteredVariant("8-11708709-A-C", null),
        unfilteredVariant("8-11708717-T-G", null),
        unfilteredVariant("8-11708725-C-A", null),
        unfilteredVariant("8-11708733-G-C", null),
        unfilteredVariant("8-11708741-A-G", null));
  }

  private static FilteredVariant filteredVariantWithStats(
      String variant,
      String classification,
      int cohortAc,
      int cohortAn,
      double cohortAf,
      int homozygotes,
      int heterozygotes,
      int clinvarPlpInTrans,
      double afRatio) {
    return new FilteredVariant()
        .variant(variant)
        .gene("GATA4")
        .classification(classification)
        .hasStats(true)
        .cohortAc(cohortAc)
        .cohortAn(cohortAn)
        .cohortAf(BigDecimal.valueOf(cohortAf))
        .homozygotes(homozygotes)
        .heterozygotes(heterozygotes)
        .clinvarPlpInTrans(clinvarPlpInTrans)
        .afRatio(BigDecimal.valueOf(afRatio));
  }

  private static FilteredVariant unfilteredVariant(String variant, String classification) {
    return new FilteredVariant()
        .variant(variant)
        .gene("GATA4")
        .classification(classification)
        .hasStats(false)
        .cohortAc(null)
        .cohortAn(null)
        .cohortAf(null)
        .homozygotes(null)
        .heterozygotes(null)
        .clinvarPlpInTrans(null)
        .afRatio(null);
  }
}
