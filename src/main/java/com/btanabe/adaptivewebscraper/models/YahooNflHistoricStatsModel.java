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
public class YahooNflHistoricStatsModel extends Model {
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
    private Double receivingYardsAfterContact;
    private Integer receivingTouchdowns;
    private Integer fumbles;
    private Integer fumblesLost;
}
