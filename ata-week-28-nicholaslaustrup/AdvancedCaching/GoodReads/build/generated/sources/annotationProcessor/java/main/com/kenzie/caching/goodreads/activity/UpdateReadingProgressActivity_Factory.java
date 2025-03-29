package com.kenzie.caching.goodreads.activity;

import com.kenzie.caching.goodreads.caching.CachingReadingLogDao;
import com.kenzie.caching.goodreads.dao.ReadingGoalDao;
import com.kenzie.caching.goodreads.dao.ReadingLogDao;
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
public final class UpdateReadingProgressActivity_Factory implements Factory<UpdateReadingProgressActivity> {
  private final Provider<ReadingLogDao> readingLogDaoProvider;

  private final Provider<ReadingGoalDao> readingGoalDaoProvider;

  private final Provider<CachingReadingLogDao> cachingReadingLogDaoProvider;

  public UpdateReadingProgressActivity_Factory(Provider<ReadingLogDao> readingLogDaoProvider,
      Provider<ReadingGoalDao> readingGoalDaoProvider,
      Provider<CachingReadingLogDao> cachingReadingLogDaoProvider) {
    this.readingLogDaoProvider = readingLogDaoProvider;
    this.readingGoalDaoProvider = readingGoalDaoProvider;
    this.cachingReadingLogDaoProvider = cachingReadingLogDaoProvider;
  }

  @Override
  public UpdateReadingProgressActivity get() {
    return newInstance(readingLogDaoProvider.get(), readingGoalDaoProvider.get(), cachingReadingLogDaoProvider.get());
  }

  public static UpdateReadingProgressActivity_Factory create(
      Provider<ReadingLogDao> readingLogDaoProvider,
      Provider<ReadingGoalDao> readingGoalDaoProvider,
      Provider<CachingReadingLogDao> cachingReadingLogDaoProvider) {
    return new UpdateReadingProgressActivity_Factory(readingLogDaoProvider, readingGoalDaoProvider, cachingReadingLogDaoProvider);
  }

  public static UpdateReadingProgressActivity newInstance(ReadingLogDao readingLogDao,
      ReadingGoalDao readingGoalDao, CachingReadingLogDao cachingReadingLogDao) {
    return new UpdateReadingProgressActivity(readingLogDao, readingGoalDao, cachingReadingLogDao);
  }
}
