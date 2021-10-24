import { React, useEffect, useState } from "react";
import MatchDetailCard from "../components/MatchDetailCard";
import MatchSmallCard from "../components/MatchSmallCard";

const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });

  useEffect(() => {
    const fetchMatches = async () => {
      const response = await fetch(
        "http://localhost:8080/team/Deccan%20Chargers"
      );
      const data = await response.json();
      console.log(data);
      setTeam(data);
    };
    fetchMatches();
  }, []);
  // [] is the dependency list, empty array will make it execute only when page loads

  return (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>

      <h3>Latest Matches</h3>
      <MatchDetailCard match={team.matches[0]}></MatchDetailCard>

      <h4>Match Details</h4>
      {team.matches.slice(1).map((m) => (
        <MatchSmallCard match={m} />
      ))}
    </div>
  );
};

export default TeamPage;
