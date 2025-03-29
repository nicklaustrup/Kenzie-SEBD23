package com.kenzie.caching.goodreads.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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
public final class NonCachingReadingLogDao_Factory implements Factory<NonCachingReadingLogDao> {
  private final Provider<DynamoDBMapper> mapperProvider;

  public NonCachingReadingLogDao_Factory(Provider<DynamoDBMapper> mapperProvider) {
    this.mapperProvider = mapperProvider;
  }

  @Override
  public NonCachingReadingLogDao get() {
    return newInstance(mapperProvider.get());
  }

  public static NonCachingReadingLogDao_Factory create(Provider<DynamoDBMapper> mapperProvider) {
    return new NonCachingReadingLogDao_Factory(mapperProvider);
  }

  public static NonCachingReadingLogDao newInstance(DynamoDBMapper mapper) {
    return new NonCachingReadingLogDao(mapper);
  }
}
