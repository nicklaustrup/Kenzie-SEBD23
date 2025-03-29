package com.kenzie.caching.leaderboard;

import dagger.internal.Factory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import redis.clients.jedis.JedisPool;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CacheClient_Factory implements Factory<CacheClient> {
  private final Provider<JedisPool> poolProvider;

  public CacheClient_Factory(Provider<JedisPool> poolProvider) {
    this.poolProvider = poolProvider;
  }

  @Override
  public CacheClient get() {
    return newInstance(poolProvider.get());
  }

  public static CacheClient_Factory create(Provider<JedisPool> poolProvider) {
    return new CacheClient_Factory(poolProvider);
  }

  public static CacheClient newInstance(JedisPool pool) {
    return new CacheClient(pool);
  }
}
