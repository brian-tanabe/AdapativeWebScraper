package com.btanabe.adaptivewebscraper.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BasketballReferenceSeasonTotalsModel implements Model {

    private String name;
    private String position;
    private Integer age;
    private String team;
    private Integer gamesPlayed;
    private Integer gamesStarted;
    private Integer minutesPlayed;
    private Integer fieldGoalsMade;
    private Integer fieldGoalsAttempted;
    private Double fieldGoalPercentage;
    private Integer threePointersMade;
    private Integer threePointersAttempted;
    private Double threePointPercentage;
    private Integer twoPointFieldGoalsMade;
    private Integer twoPointFieldGoalsAttempted;
    private Double twoPointFieldGoalPercentage;
    private Double effectiveFieldGoalPercentage;
    private Integer freeThrowsMade;
    private Integer freeThrowsAttempted;
    private Double freeThrowPercentage;
    private Integer offensiveRebounds;
    private Integer defensiveRebounds;
    private Integer totalRebounds;
    private Integer assists;
    private Integer steals;
    private Integer blocks;
    private Integer turnovers;
    private Integer personalFouls;
    private Integer points;

    @Override
    public String toString() {
        return String.format("name=[%s], position=[%s], age=[%d], team=[%s], gamesPlayed=[%d], gamesStarted=[%d], minutesPlayed=[%d], fieldGoalsMade=[%d], fieldGoalsAttempted=[%d], fieldGoalPercentage=[%d], threePointersMade=[%d], threePointersAttempted=[%d], threePointPercentage=[%d], twoPointFieldGoalsMade=[%d], twoPointFieldGoalsAttempted=[%d], twoPointFieldGoalPercentage=[%d], effectiveFieldGoalPercentage=[%d], freeThrowsMade=[%d], freeThrowsAttempted=[%d], freeThrowPercentage=[%d], offensiveRebounds=[%d], defensiveRebounds=[%d], totalRebounds=[%d], assists=[%d], steals=[%d], blocks=[%d], turnovers=[%d], personalFouls=[%d], points=[%d]", name, position, age, team, gamesPlayed, gamesStarted, minutesPlayed, fieldGoalsMade, fieldGoalsAttempted, fieldGoalPercentage, threePointersMade, threePointersAttempted, threePointPercentage, twoPointFieldGoalsMade, twoPointFieldGoalsAttempted, twoPointFieldGoalPercentage, effectiveFieldGoalPercentage, freeThrowsMade, freeThrowsAttempted, freeThrowPercentage, offensiveRebounds, defensiveRebounds, totalRebounds, assists, steals, blocks, turnovers, personalFouls, points);
    }
}
