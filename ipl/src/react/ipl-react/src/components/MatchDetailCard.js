import { React } from "react";

const MatchDetailCard = ({ match = {} }) => {
  return (
    <div className="MatchDetailCard">
      <h4>
        {match.team1} vs {match.team2}
      </h4>
    </div>
  );
};

export default MatchDetailCard;
