package com.kenzie.caching.leaderboard;

import com.kenzie.caching.leaderboard.resources.datasource.Entry;
import com.kenzie.caching.leaderboard.resources.datasource.LeaderboardDao;

import javax.inject.Inject;
import java.util.Optional;

public class CachingLeaderboardDao {
    private final LeaderboardDao dataSource;
    private final CacheClient cache;

    /**
     * Constructor.
     *
     * @param dataSource LeaderboardDAO object
     * @param cache      CacheClient object
     */
    @Inject
    public CachingLeaderboardDao(LeaderboardDao dataSource, CacheClient cache) {
        this.dataSource = dataSource;
        this.cache = cache;
    }

    /**
     * Retrieves score associated with the specified user. Should use the cache when possible, but the dataSource object
     * is our source of truth for high scores. The TTL for our high scores should be 5 minutes.
     *
     * PARTICIPANTS: replace return 0 with your implementation of this method.
     *
     * @param username String representing player username
     * @return long representing score associated with username
     */
    public long getHighScore(String username) {
        return cache.getValue(username)
                .map(Long::valueOf)
                .orElseGet(() -> getHighScoreFromDataSource(username));
    }

    private Long getHighScoreFromDataSource(String username) {
            Entry dataSourceScore = dataSource.getEntry(username);
            cache.setValue(dataSourceScore.getUsername(), 5*60, String.valueOf(dataSourceScore.getScore()));
            return dataSourceScore.getScore();
    }

    public boolean invalidateScore(String username){
        return cache.invalidate(username);
    }
}
