package com.btanabe.adaptivewebscraper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Brian on 7/3/16.
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class YahooNflHistoricStatsModel implements Model {
    private Integer season;
    private Integer playerId;
    private String name;
    private String team;
    private String position;
    private Integer gamesPlayed;
    private Integer passingCompletions;
    private Integer passingAttempts;
    private Double completionPercentage;
    private Integer passingYards;
    private Double passingYardsPerGame;
    private Double passingYardsPerAttempt;
    private Integer passingTouchdowns;
    private Integer interceptions;
    private Integer rushingAttempts;
    private Integer rushingYards;
    private Double rushingYardsPerGame;
    private Double rushingYardsPerAttempt;
    private Integer rushingTouchdowns;
    private Integer receptions;
    private Integer targets;
    private Integer receivingYards;
    private Double receivingYardsPerGame;
    private Double receivingYardsPerReception;
    private Double receivingYardsAfterCatch;
    private Integer receivingTouchdowns;
    private Integer sacksTaken;
    private Integer sackYardsLost;
    private Integer fumbles;
    private Integer fumblesLost;

    @Override
    public String toString() {
        return String.format("season=[%s], playerId=[%s], name=[%s], team=[%s], position=[%s], gamesPlayed=[%s], passingCompletions=[%s], passingAttempts=[%s], completionPercentage=[%s], passingYards=[%s], passingYardsPerGame=[%s], passingYardsPerAttempt=[%s], passingTouchdowns=[%s], interceptions=[%s], rushingAttempts=[%s], rushingYards=[%s], rushingYardsPerGame=[%s], rushingYardsPerAttempt=[%s], rushingTouchdowns=[%s], receptions=[%s], targets=[%s], receivingYards=[%s], receivingYardsPerGame=[%s], receivingYardsPerReception=[%s], receivingYardsAfterCatch=[%s], receivingTouchdowns=[%s], sacksTaken=[%s], sackYardsTaken=[%s], fumbles=[%s], fumblesLost=[%s]", season, playerId, name, team, position, gamesPlayed, passingCompletions, passingAttempts, completionPercentage, passingYards, passingYardsPerGame, passingYardsPerAttempt, passingTouchdowns, interceptions, rushingAttempts, rushingYards, rushingYardsPerGame, rushingYardsPerAttempt, rushingTouchdowns, receptions, targets, receivingYards, receivingYardsPerGame, receivingYardsPerReception, receivingYardsAfterCatch, receivingTouchdowns, sacksTaken, sackYardsLost, fumbles, fumblesLost);
    }
}
