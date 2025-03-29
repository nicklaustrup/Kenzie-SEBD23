package com.kenzie.caching.goodreads.activity;

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
public final class MarkBookAsCurrentlyReadingActivity_Factory implements Factory<MarkBookAsCurrentlyReadingActivity> {
  private final Provider<ReadingLogDao> readingLogDaoProvider;

  public MarkBookAsCurrentlyReadingActivity_Factory(Provider<ReadingLogDao> readingLogDaoProvider) {
    this.readingLogDaoProvider = readingLogDaoProvider;
  }

  @Override
  public MarkBookAsCurrentlyReadingActivity get() {
    return newInstance(readingLogDaoProvider.get());
  }

  public static MarkBookAsCurrentlyReadingActivity_Factory create(
      Provider<ReadingLogDao> readingLogDaoProvider) {
    return new MarkBookAsCurrentlyReadingActivity_Factory(readingLogDaoProvider);
  }

  public static MarkBookAsCurrentlyReadingActivity newInstance(ReadingLogDao readingLogDao) {
    return new MarkBookAsCurrentlyReadingActivity(readingLogDao);
  }
}
