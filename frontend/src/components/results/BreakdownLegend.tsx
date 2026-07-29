import type { BreakdownSegment } from "../../types/results";
import styles from "./BreakdownLegend.module.css";

interface BreakdownLegendProps {
  segments: BreakdownSegment[];
}

export default function BreakdownLegend({ segments }: BreakdownLegendProps) {
  return (
    <div className={styles.strip}>
      {segments.map((segment) => (
        <span key={segment.label} className={styles.chip}>
          <span className={styles.dot} style={{ background: segment.color }} />
          {segment.label} {segment.percent}%
        </span>
      ))}
    </div>
  );
}
