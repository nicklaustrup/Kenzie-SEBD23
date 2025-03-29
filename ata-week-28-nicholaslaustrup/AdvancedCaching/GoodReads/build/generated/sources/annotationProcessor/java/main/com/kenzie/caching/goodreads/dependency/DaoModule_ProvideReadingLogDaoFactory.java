package com.kenzie.caching.goodreads.dependency;

import com.kenzie.caching.goodreads.dao.NonCachingReadingLogDao;
import com.kenzie.caching.goodreads.dao.ReadingLogDao;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DaoModule_ProvideReadingLogDaoFactory implements Factory<ReadingLogDao> {
  private final DaoModule module;

  private final Provider<NonCachingReadingLogDao> readingLogDaoProvider;

  public DaoModule_ProvideReadingLogDaoFactory(DaoModule module,
      Provider<NonCachingReadingLogDao> readingLogDaoProvider) {
    this.module = module;
    this.readingLogDaoProvider = readingLogDaoProvider;
  }

  @Override
  public ReadingLogDao get() {
    return provideReadingLogDao(module, readingLogDaoProvider.get());
  }

  public static DaoModule_ProvideReadingLogDaoFactory create(DaoModule module,
      Provider<NonCachingReadingLogDao> readingLogDaoProvider) {
    return new DaoModule_ProvideReadingLogDaoFactory(module, readingLogDaoProvider);
  }

  public static ReadingLogDao provideReadingLogDao(DaoModule instance,
      NonCachingReadingLogDao readingLogDao) {
    return Preconditions.checkNotNullFromProvides(instance.provideReadingLogDao(readingLogDao));
  }
}
