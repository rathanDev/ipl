package org.jana.ipl.data;

import org.jana.ipl.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;
    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, EntityManager em) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        // super.afterJob(jobExecution);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            // jdbcTemplate.query("SELECT team1, team2, date FROM match",
            // (rs, row) -> "Team1:" + rs.getString(1) + " Team2:" + rs.getString(2) + "
            // Date:" + rs.getString(3))
            // .forEach(match -> System.out.println(match));

            // select distinct team1 from match union select distinct team2 from match
            // But JPA doensn't allow union

            Map<String, Team> teamData = new HashMap<>();

            em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .forEach(team -> teamData.put(team.getTeamName(), team));

            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .forEach(e -> {
                        Team team = teamData.get(e.getTeamName());
                        team.setTotalMatches(team.getTotalMatches() + e.getTotalMatches());
                    });

            em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList().forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        if (team != null)
                            team.setTotalWins((long) e[1]);
                    });

            teamData.values().forEach(em::persist);

            System.out.print(teamData.values());
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // TODO Auto-generated method stub
        super.beforeJob(jobExecution);
    }

}
