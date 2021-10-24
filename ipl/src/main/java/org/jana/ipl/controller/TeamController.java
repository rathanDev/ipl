package org.jana.ipl.controller;

import org.jana.ipl.model.Match;
import org.jana.ipl.model.Team;
import org.jana.ipl.repo.MatchRepo;
import org.jana.ipl.repo.TeamRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TeamController {

    private final TeamRepo teamRepo;
    private final MatchRepo matchRepo;

    public TeamController(TeamRepo teamRepo,
                          MatchRepo matchRepo) {
        this.teamRepo = teamRepo;
        this.matchRepo = matchRepo;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Optional<Team> teamOpt = teamRepo.findByTeamName(teamName);
        if (teamOpt.isEmpty()) {
            return null;
        }
        Team team = teamOpt.get();

        List<Match> matches = matchRepo.findLatestMatchesByTeam(teamName, 4);
        team.setMatches(matches);
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return matchRepo.getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }

}
