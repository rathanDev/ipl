package org.jana.ipl.controller;

import org.jana.ipl.model.Team;
import org.jana.ipl.repo.TeamRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamRepo teamRepo;

    public TeamController(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        return teamRepo.findByTeamName(teamName);
    }

}
