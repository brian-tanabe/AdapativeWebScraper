package com.btanabe.adaptivewebscraper.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by Brian on 5/1/16.
 */
@Builder
@Data
@EqualsAndHashCode
public class EspnNflProjectionModel {
    private Integer playerId;
    private Integer rank;
    private String name;
    private String team;
    private String position;
    private Integer passingCompletions;
    private Integer passingAttempts;
    private Integer passingYards;
    private Integer passingTouchdowns;
    private Integer interceptions;
    private Integer rushingAttempts;
    private Integer rushingYards;
    private Integer rushingTouchdowns;
    private Integer receptions;
    private Integer receivingYards;
    private Integer receivingTouchdowns;
    private Double fantasyPoints;

    @Override
    public String toString() {
        return String.format("rank=[%d], name=[%s], team=[%s], position=[%s], passingCompletions=[%d], passingAttempts=[%d], passingYards=[%d], passingTouchdowns=[%d], interceptions=[%d], rushingAttempts=[%d], rushingYards=[%d], rushingTouchdowns=[%d], receptions=[%d], receivingYards=[%d], receivingTouchdowns=[%d], fantasyPoints=[%f]", rank, name, team, position, passingCompletions, passingAttempts, passingYards, passingTouchdowns, interceptions, rushingAttempts, rushingYards, rushingTouchdowns, receptions, receivingYards, receivingTouchdowns, fantasyPoints);
    }
}
