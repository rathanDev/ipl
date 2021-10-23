package org.jana.ipl.repo;

import org.jana.ipl.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends CrudRepository<Team, Long> {

    public Team findByTeamName(String teamName);

}
