package com.kenzie.caching.goodreads.caching;

import com.kenzie.caching.goodreads.dao.NonCachingReadingLogDao;
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
public final class CachingReadingLogDao_Factory implements Factory<CachingReadingLogDao> {
  private final Provider<CacheClient> cacheClientProvider;

  private final Provider<NonCachingReadingLogDao> readingDoaProvider;

  public CachingReadingLogDao_Factory(Provider<CacheClient> cacheClientProvider,
      Provider<NonCachingReadingLogDao> readingDoaProvider) {
    this.cacheClientProvider = cacheClientProvider;
    this.readingDoaProvider = readingDoaProvider;
  }

  @Override
  public CachingReadingLogDao get() {
    return newInstance(cacheClientProvider.get(), readingDoaProvider.get());
  }

  public static CachingReadingLogDao_Factory create(Provider<CacheClient> cacheClientProvider,
      Provider<NonCachingReadingLogDao> readingDoaProvider) {
    return new CachingReadingLogDao_Factory(cacheClientProvider, readingDoaProvider);
  }

  public static CachingReadingLogDao newInstance(CacheClient cacheClient,
      NonCachingReadingLogDao readingDoa) {
    return new CachingReadingLogDao(cacheClient, readingDoa);
  }
}
