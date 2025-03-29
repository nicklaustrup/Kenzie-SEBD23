package com.kenzie.caching.goodreads.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.caching.goodreads.activity.GetGoalProgressActivity;
import com.kenzie.caching.goodreads.activity.MarkBookAsCurrentlyReadingActivity;
import com.kenzie.caching.goodreads.activity.MarkBookAsReadActivity;
import com.kenzie.caching.goodreads.activity.SetGoalActivity;
import com.kenzie.caching.goodreads.activity.UpdateReadingProgressActivity;
import com.kenzie.caching.goodreads.caching.CacheClient;
import com.kenzie.caching.goodreads.caching.CachingReadingLogDao;
import com.kenzie.caching.goodreads.dao.NonCachingReadingLogDao;
import com.kenzie.caching.goodreads.dao.NonCachingReadingLogDao_Factory;
import com.kenzie.caching.goodreads.dao.ReadingGoalDao;
import com.kenzie.caching.goodreads.dao.ReadingLogDao;
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
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

  private Provider<NonCachingReadingLogDao> nonCachingReadingLogDaoProvider;

  private Provider<ReadingLogDao> provideReadingLogDaoProvider;

  private Provider<JedisPool> provideJedisPoolProvider;

  private DaggerServiceComponent(DaoModule daoModuleParam, CachingModule cachingModuleParam) {

    initialize(daoModuleParam, cachingModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  private CacheClient cacheClient() {
    return new CacheClient(provideJedisPoolProvider.get());
  }

  private NonCachingReadingLogDao nonCachingReadingLogDao() {
    return new NonCachingReadingLogDao(provideDynamoDBMapperProvider.get());
  }

  private CachingReadingLogDao cachingReadingLogDao() {
    return new CachingReadingLogDao(cacheClient(), nonCachingReadingLogDao());
  }

  private ReadingGoalDao readingGoalDao() {
    return new ReadingGoalDao(provideDynamoDBMapperProvider.get());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final DaoModule daoModuleParam, final CachingModule cachingModuleParam) {
    this.provideDynamoDBMapperProvider = DoubleCheck.provider(DaoModule_ProvideDynamoDBMapperFactory.create(daoModuleParam));
    this.nonCachingReadingLogDaoProvider = NonCachingReadingLogDao_Factory.create(provideDynamoDBMapperProvider);
    this.provideReadingLogDaoProvider = DoubleCheck.provider(DaoModule_ProvideReadingLogDaoFactory.create(daoModuleParam, nonCachingReadingLogDaoProvider));
    this.provideJedisPoolProvider = DoubleCheck.provider(CachingModule_ProvideJedisPoolFactory.create(cachingModuleParam));
  }

  @Override
  public MarkBookAsReadActivity provideMarkBookAsReadActivity() {
    return new MarkBookAsReadActivity(provideReadingLogDaoProvider.get(), cachingReadingLogDao());
  }

  @Override
  public UpdateReadingProgressActivity provideUpdateReadingProgressActivity() {
    return new UpdateReadingProgressActivity(provideReadingLogDaoProvider.get(), readingGoalDao(), cachingReadingLogDao());
  }

  @Override
  public MarkBookAsCurrentlyReadingActivity provideMarkBookAsCurrentlyReadingActivity() {
    return new MarkBookAsCurrentlyReadingActivity(provideReadingLogDaoProvider.get());
  }

  @Override
  public SetGoalActivity provideSetGoalActivity() {
    return new SetGoalActivity(readingGoalDao());
  }

  @Override
  public GetGoalProgressActivity provideGetGoalProgressActivity() {
    return new GetGoalProgressActivity(provideReadingLogDaoProvider.get(), readingGoalDao(), cachingReadingLogDao());
  }

  @Override
  public JedisPool provideJedisPool() {
    return provideJedisPoolProvider.get();
  }

  public static final class Builder {
    private DaoModule daoModule;

    private CachingModule cachingModule;

    private Builder() {
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }

    public Builder cachingModule(CachingModule cachingModule) {
      this.cachingModule = Preconditions.checkNotNull(cachingModule);
      return this;
    }

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      if (cachingModule == null) {
        this.cachingModule = new CachingModule();
      }
      return new DaggerServiceComponent(daoModule, cachingModule);
    }
  }
}
