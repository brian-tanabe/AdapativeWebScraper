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
    private Integer fumbles;
    private Integer fumblesLost;

    @Override
    public String toString() {
        return String.format("season=[%d], playerId=[%d], name=[%s], team=[%s], position=[%s], gamesPlayed=[%d], passingCompletions=[%d], passingAttempts=[%d], completionPercentage=[%d], passingYards=[%d], passingTouchdowns=[%d], interceptions=[%d], rushingAttempts=[%d], rushingYards=[%d], rushingYardsPerGame=[%d], rushingYardsPerAttempt=[%d], rushingTouchdowns=[%d], receptions=[%d], targets=[%d], receivingYards=[%d], receivingYardsPerGame=[%d], receivingYardsPerReception=[%d], receivingYardsAfterCatch=[%d], receivingTouchdowns=[%d], fumbles=[%d], fumblesLost", season, playerId, name, team, position, gamesPlayed, passingCompletions, passingAttempts, completionPercentage, passingYards, passingTouchdowns, interceptions, rushingAttempts, rushingYards, rushingYardsPerGame, rushingYardsPerAttempt, rushingTouchdowns, receptions, targets, receivingYards, receivingYardsPerGame, receivingYardsPerReception, receivingYardsAfterCatch, receivingTouchdowns, fumbles, fumblesLost);
    }
}
