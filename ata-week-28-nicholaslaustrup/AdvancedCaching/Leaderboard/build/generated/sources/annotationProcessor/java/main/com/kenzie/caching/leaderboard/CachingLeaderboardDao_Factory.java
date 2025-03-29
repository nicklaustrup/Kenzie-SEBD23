package com.kenzie.caching.leaderboard;

import com.kenzie.caching.leaderboard.resources.datasource.LeaderboardDao;
import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CachingLeaderboardDao_Factory implements Factory<CachingLeaderboardDao> {
  private final Provider<LeaderboardDao> dataSourceProvider;

  private final Provider<CacheClient> cacheProvider;

  public CachingLeaderboardDao_Factory(Provider<LeaderboardDao> dataSourceProvider,
      Provider<CacheClient> cacheProvider) {
    this.dataSourceProvider = dataSourceProvider;
    this.cacheProvider = cacheProvider;
  }

  @Override
  public CachingLeaderboardDao get() {
    return newInstance(dataSourceProvider.get(), cacheProvider.get());
  }

  public static CachingLeaderboardDao_Factory create(Provider<LeaderboardDao> dataSourceProvider,
      Provider<CacheClient> cacheProvider) {
    return new CachingLeaderboardDao_Factory(dataSourceProvider, cacheProvider);
  }

  public static CachingLeaderboardDao newInstance(LeaderboardDao dataSource, CacheClient cache) {
    return new CachingLeaderboardDao(dataSource, cache);
  }
}
