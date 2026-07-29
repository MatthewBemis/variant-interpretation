import type { ReactNode } from "react";
import type { BreakdownSegment } from "../../types/results";
import styles from "./Donut.module.css";

interface DonutProps {
  segments: BreakdownSegment[];
  centerLabel: ReactNode;
}

export default function Donut({ segments, centerLabel }: DonutProps) {
  let cumulative = 0;
  const stops = segments
    .map((segment) => {
      const start = cumulative;
      cumulative += segment.percent;
      return `${segment.color} ${start}% ${cumulative}%`;
    })
    .join(", ");

  return (
    <div className={styles.wrap}>
      <div className={styles.donut} style={{ background: `conic-gradient(${stops})` }}>
        <div className={styles.center}>{centerLabel}</div>
      </div>
    </div>
  );
}
