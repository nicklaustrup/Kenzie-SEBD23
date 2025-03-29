package com.kenzie.caching.goodreads.caching;

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
  private final Provider<JedisPool> jedisPoolProvider;

  public CacheClient_Factory(Provider<JedisPool> jedisPoolProvider) {
    this.jedisPoolProvider = jedisPoolProvider;
  }

  @Override
  public CacheClient get() {
    return newInstance(jedisPoolProvider.get());
  }

  public static CacheClient_Factory create(Provider<JedisPool> jedisPoolProvider) {
    return new CacheClient_Factory(jedisPoolProvider);
  }

  public static CacheClient newInstance(JedisPool jedisPool) {
    return new CacheClient(jedisPool);
  }
}
