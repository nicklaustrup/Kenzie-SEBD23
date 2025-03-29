package com.kenzie.caching.goodreads.activity;

import com.kenzie.caching.goodreads.caching.CachingReadingLogDao;
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
public final class MarkBookAsReadActivity_Factory implements Factory<MarkBookAsReadActivity> {
  private final Provider<ReadingLogDao> readingLogDaoProvider;

  private final Provider<CachingReadingLogDao> cachingReadingLogDaoProvider;

  public MarkBookAsReadActivity_Factory(Provider<ReadingLogDao> readingLogDaoProvider,
      Provider<CachingReadingLogDao> cachingReadingLogDaoProvider) {
    this.readingLogDaoProvider = readingLogDaoProvider;
    this.cachingReadingLogDaoProvider = cachingReadingLogDaoProvider;
  }

  @Override
  public MarkBookAsReadActivity get() {
    return newInstance(readingLogDaoProvider.get(), cachingReadingLogDaoProvider.get());
  }

  public static MarkBookAsReadActivity_Factory create(Provider<ReadingLogDao> readingLogDaoProvider,
      Provider<CachingReadingLogDao> cachingReadingLogDaoProvider) {
    return new MarkBookAsReadActivity_Factory(readingLogDaoProvider, cachingReadingLogDaoProvider);
  }

  public static MarkBookAsReadActivity newInstance(ReadingLogDao readingLogDao,
      CachingReadingLogDao cachingReadingLogDao) {
    return new MarkBookAsReadActivity(readingLogDao, cachingReadingLogDao);
  }
}
