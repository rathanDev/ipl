import { React } from "react";

const MatchDetailCard = ({ match = {}, teamName }) => {
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;

  return (
    <div className="MatchDetailCard">
      <h4>vs {otherTeam}</h4>
    </div>
  );
};

export default MatchDetailCard;
