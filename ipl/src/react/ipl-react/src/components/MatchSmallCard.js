import { React } from "react";
import { Link } from "react-router-dom";

const MatchSmallCard = ({ teamName, match = {} }) => {
  const otherTeam = match.team1 === teamName ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;

  return (
    <div className="MatchSmallCard">
      <h4>
        vs <Link to={otherTeamRoute}> {otherTeam} </Link>
      </h4>
      <p>
        {match.matchWinner} won by {match.resultMargin} {match.result}
      </p>
    </div>
  );
};

export default MatchSmallCard;
