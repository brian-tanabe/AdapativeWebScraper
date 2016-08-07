package com.btanabe.adaptivewebscraper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Brian on 5/1/16.
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EspnNflProjectionModel implements Model {
    private Integer playerId;
    private Integer rank;
    private String name;
    private String team;
    private String position;
    private Double passingCompletions;
    private Double passingAttempts;
    private Double passingYards;
    private Double passingTouchdowns;
    private Double interceptions;
    private Double rushingAttempts;
    private Double rushingYards;
    private Double rushingTouchdowns;
    private Double receptions;
    private Double receivingYards;
    private Double receivingTouchdowns;
    private Double fantasyPoints;

    @Override
    public String toString() {
        return String.format("playerId=[%d], rank=[%d], name=[%s], team=[%s], position=[%s], passingCompletions=[%f], passingAttempts=[%f], passingYards=[%f], passingTouchdowns=[%f], interceptions=[%f], rushingAttempts=[%f], rushingYards=[%f], rushingTouchdowns=[%f], receptions=[%f], receivingYards=[%f], receivingTouchdowns=[%f], fantasyPoints=[%f]", playerId, rank, name, team, position, passingCompletions, passingAttempts, passingYards, passingTouchdowns, interceptions, rushingAttempts, rushingYards, rushingTouchdowns, receptions, receivingYards, receivingTouchdowns, fantasyPoints);
    }
}
