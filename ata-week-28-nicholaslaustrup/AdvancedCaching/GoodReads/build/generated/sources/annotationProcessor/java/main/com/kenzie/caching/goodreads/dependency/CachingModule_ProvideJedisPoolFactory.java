package com.kenzie.caching.goodreads.dependency;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import redis.clients.jedis.JedisPool;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class CachingModule_ProvideJedisPoolFactory implements Factory<JedisPool> {
  private final CachingModule module;

  public CachingModule_ProvideJedisPoolFactory(CachingModule module) {
    this.module = module;
  }

  @Override
  public JedisPool get() {
    return provideJedisPool(module);
  }

  public static CachingModule_ProvideJedisPoolFactory create(CachingModule module) {
    return new CachingModule_ProvideJedisPoolFactory(module);
  }

  public static JedisPool provideJedisPool(CachingModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideJedisPool());
  }
}
