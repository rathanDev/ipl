package org.jana.ipl.data;

import java.time.LocalDate;

import org.jana.ipl.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput mi) {
        log.debug("Process input {}", mi);

        Match m = new Match();
        m.setId(Long.parseLong(mi.getId()));
        m.setCity(mi.getCity());
        m.setDate(LocalDate.parse(mi.getDate()));
        m.setPlayerOfMatch(mi.getPlayer_of_match());
        m.setVenue(mi.getVenue());

        //
        // flow integration
        // spring integration

        String firstInningsTeam, secondInningsTeam;
        if ("bat".equals(mi.getToss_decision())) {
            firstInningsTeam = mi.getToss_winner();
            secondInningsTeam = mi.getToss_winner().equals(mi.getTeam1()) ? mi.getTeam2() : mi.getTeam1();
        } else {
            secondInningsTeam = mi.getToss_winner();
            firstInningsTeam = mi.getToss_winner().equals(mi.getTeam1()) ? mi.getTeam2() : mi.getTeam1();
        }

        m.setTeam1(firstInningsTeam);
        m.setTeam2(secondInningsTeam);

        m.setTossWinner(mi.getToss_winner());
        m.setTossDecision(mi.getToss_decision());
        m.setMatchWinner(mi.getWinner());

        m.setResult(mi.getResult());
        m.setResultMargin(mi.getResult_margin());
        m.setUmpire1(mi.getUmpire1());
        m.setUmpire2(mi.getUmpire2());

        return m;
    }

}
