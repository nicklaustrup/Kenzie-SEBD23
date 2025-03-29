package com.kenzie.dynamodbindexdesign.littleleague.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.kenzie.dynamodbindexdesign.littleleague.model.Match;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.*;
import javax.inject.Inject;

public class MatchDao {
    private DynamoDBMapper mapper;
    private DynamoDBQueryExpression<Match> queryExpression = new DynamoDBQueryExpression<Match>();

    /**
     * Allows access to and manipulation of Match objects from the data store.
     * @param mapper Access to DynamoDB
     */
    @Inject
    public MatchDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves all home matches for the given team between startDate and endDate, inclusive.
     * @param team The name of the team to fetch matches for
     * @param startDate The first date to return matches for
     * @param endDate The last date to return matches for
     * @return A list of the matches for the given team between the given dates
     */
    public List<Match> getHomeMatchesForTeam(String team, String startDate, String endDate) {
        // PARTICIPANTS: Implement.
        //               use DynamoDBMapper's query method to retrieve all home games
        //               for the given team in the given date range.

        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":homeTeam", new AttributeValue().withS(team));
        valueMap.put(":startDate", new AttributeValue().withS(startDate));
        valueMap.put(":endDate", new AttributeValue().withS(endDate));

        queryExpression.withIndexName(Match.HOME_TEAM_MATCHES_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("homeTeam = :homeTeam and matchDate between :startDate and :endDate")
                .withExpressionAttributeValues(valueMap);

        return mapper.query(Match.class, queryExpression);
    }

    /**
     * Retrieves all away matches for the given team between startDate and endDate, inclusive.
     * @param team The name of the team to fetch matches for
     * @param startDate The first date to return matches for
     * @param endDate The last date to return matches for
     * @return A list of the matches for the given team between the given dates
     */
    public List<Match> getAwayMatchesForTeam(String team, String startDate, String endDate) {
        // PARTICIPANTS: Implement.
        //               use DynamoDBMapper's query method to retrieve all away games
        //               for the given team in the given date range.
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":awayTeam", new AttributeValue().withS(team));
        valueMap.put(":startDate", new AttributeValue().withS(startDate));
        valueMap.put(":endDate", new AttributeValue().withS(endDate));

        queryExpression.withIndexName(Match.AWAY_TEAM_MATCHES_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("awayTeam = :awayTeam and matchDate between :startDate and :endDate")
                .withExpressionAttributeValues(valueMap);

        return mapper.query(Match.class, queryExpression);
    }

    /**
     * Retrieves all matches for the given team between startDate and endDate, inclusive.
     * @param team The name of the team to fetch matches for
     * @param startDate The first date to return matches for
     * @param endDate The last date to return matches for
     * @return A list of the matches for the given team between the given dates
     */
    public List<Match> getAllMatchesForTeam(String team, String startDate, String endDate) {
        List<Match> allTeamMatchList = new ArrayList<>();
        allTeamMatchList.addAll(getHomeMatchesForTeam(team, startDate, endDate));
        allTeamMatchList.addAll(getAwayMatchesForTeam(team, startDate, endDate));
        return allTeamMatchList;
    }
}
