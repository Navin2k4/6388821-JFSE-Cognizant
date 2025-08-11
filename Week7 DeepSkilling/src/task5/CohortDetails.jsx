import React from "react";
import styles from "./CohortDetails.module.css";

const CohortDetails = () => {
  const cohorts = [
    {
      id: "INTADMDF10",
      type: ".NET FSD",
      startedOn: "22-Feb-2022",
      status: "Scheduled",
      coach: "Aathma",
      trainer: "Jojo Jose",
    },
    {
      id: "ADM21JF014",
      type: "Java FSD",
      startedOn: "10-Sep-2021",
      status: "Ongoing",
      coach: "Apoorv",
      trainer: "Elisa Smith",
    },
    {
      id: "CDBJF21025",
      type: "Java FSD",
      startedOn: "24-Dec-2021",
      status: "Ongoing",
      coach: "Aathma",
      trainer: "John Doe",
    },
  ];

  return (
    <div>
      <h1 style={{ textAlign: "center" }}>Cohorts Details</h1>
      <div
        style={{ display: "flex", justifyContent: "center", flexWrap: "wrap" }}
      >
        {cohorts.map((cohort) => (
          <div key={cohort.id} className={styles.box}>
            <h3
              style={{ color: cohort.status === "Ongoing" ? "green" : "blue" }}
            >
              {cohort.id} - {cohort.type}
            </h3>
            <dl>
              <dt>Started On</dt>
              <dd>{cohort.startedOn}</dd>
              <dt>Current Status</dt>
              <dd>{cohort.status}</dd>
              <dt>Coach</dt>
              <dd>{cohort.coach}</dd>
              <dt>Trainer</dt>
              <dd>{cohort.trainer}</dd>
            </dl>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CohortDetails;
