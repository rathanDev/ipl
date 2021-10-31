import { React, useState, useEffect } from "react";
import { useParams } from "react-router";
import MatchDetailCard from "../components/MatchDetailCard";

const MatchPage = () => {
  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(() => {
    console.log(`MatchPage teamName:${teamName} year:${year}`);
    const fetchMatches = async () => {
      const response = await fetch(
        `http://localhost:8080/team/${teamName}/matches?year=${year}`
      );
      const data = await response.json();
      setMatches(data);
    };
    fetchMatches();
  }, []);

  return (
    <div className="MatchPage">
      <h1>Match Page</h1>
      {matches.map((m) => (
        <MatchDetailCard teamName={teamName} match={m} />
      ))}
    </div>
  );
};

export default MatchPage;
