package com.kenzie.caching.leaderboard;

import com.kenzie.caching.leaderboard.resources.GameServer;
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
public final class StartGameActivity_Factory implements Factory<StartGameActivity> {
  private final Provider<GameServer> gameServerProvider;

  private final Provider<CachingLeaderboardDao> cachingLeaderboardDaoProvider;

  public StartGameActivity_Factory(Provider<GameServer> gameServerProvider,
      Provider<CachingLeaderboardDao> cachingLeaderboardDaoProvider) {
    this.gameServerProvider = gameServerProvider;
    this.cachingLeaderboardDaoProvider = cachingLeaderboardDaoProvider;
  }

  @Override
  public StartGameActivity get() {
    return newInstance(gameServerProvider.get(), cachingLeaderboardDaoProvider.get());
  }

  public static StartGameActivity_Factory create(Provider<GameServer> gameServerProvider,
      Provider<CachingLeaderboardDao> cachingLeaderboardDaoProvider) {
    return new StartGameActivity_Factory(gameServerProvider, cachingLeaderboardDaoProvider);
  }

  public static StartGameActivity newInstance(GameServer gameServer,
      CachingLeaderboardDao cachingLeaderboardDao) {
    return new StartGameActivity(gameServer, cachingLeaderboardDao);
  }
}
