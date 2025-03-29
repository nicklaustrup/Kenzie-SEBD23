package com.kenzie.caching.goodreads.activity;

import com.kenzie.caching.goodreads.dao.ReadingGoalDao;
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
public final class SetGoalActivity_Factory implements Factory<SetGoalActivity> {
  private final Provider<ReadingGoalDao> readingGoalDaoProvider;

  public SetGoalActivity_Factory(Provider<ReadingGoalDao> readingGoalDaoProvider) {
    this.readingGoalDaoProvider = readingGoalDaoProvider;
  }

  @Override
  public SetGoalActivity get() {
    return newInstance(readingGoalDaoProvider.get());
  }

  public static SetGoalActivity_Factory create(Provider<ReadingGoalDao> readingGoalDaoProvider) {
    return new SetGoalActivity_Factory(readingGoalDaoProvider);
  }

  public static SetGoalActivity newInstance(ReadingGoalDao readingGoalDao) {
    return new SetGoalActivity(readingGoalDao);
  }
}
