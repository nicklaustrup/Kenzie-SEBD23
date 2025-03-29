package com.kenzie.caching.leaderboard.resources.modules;

import com.kenzie.caching.leaderboard.CacheClient;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
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
public final class DaggerClientComponent implements ClientComponent {
  private Provider<JedisPool> provideJedisPoolProvider;

  private DaggerClientComponent(CachingModule cachingModuleParam) {

    initialize(cachingModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ClientComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final CachingModule cachingModuleParam) {
    this.provideJedisPoolProvider = DoubleCheck.provider(CachingModule_ProvideJedisPoolFactory.create(cachingModuleParam));
  }

  @Override
  public CacheClient buildClient() {
    return new CacheClient(provideJedisPoolProvider.get());
  }

  public static final class Builder {
    private CachingModule cachingModule;

    private Builder() {
    }

    public Builder cachingModule(CachingModule cachingModule) {
      this.cachingModule = Preconditions.checkNotNull(cachingModule);
      return this;
    }

    public ClientComponent build() {
      if (cachingModule == null) {
        this.cachingModule = new CachingModule();
      }
      return new DaggerClientComponent(cachingModule);
    }
  }
}
