package org.jana.ipl.repo;

import org.jana.ipl.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepo extends CrudRepository<Team, Long> {

    Optional<Team> findByTeamName(String teamName);

}
