import { React, useEffect, useState } from "react";
import MatchDetailCard from "../components/MatchDetailCard";
import MatchSmallCard from "../components/MatchSmallCard";
import { useParams } from "react-router-dom";

const TeamPage = () => {
  const [team, setTeam] = useState({ matches: [] });
  const { teamName } = useParams();

  useEffect(() => {
    console.log(`teamName: ${teamName}`);
    const fetchMatches = async () => {
      const response = await fetch(`http://localhost:8080/team/${teamName}`);
      const data = await response.json();
      console.log(data);
      setTeam(data);
    };
    fetchMatches();
  }, [teamName]);
  // [] is the dependency list
  // empty array will make it execute only when page loads

  if (!team || !team.teamName) {
    return <h1>Team Not found</h1>;
  }

  return (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>

      <MatchDetailCard
        teamName={team.teamName}
        match={team.matches[0]}
      ></MatchDetailCard>

      <h4>Match Details</h4>
      {team.matches.slice(1).map((m) => (
        <MatchSmallCard teamName={team.teamName} match={m} />
      ))}
    </div>
  );
};

export default TeamPage;
