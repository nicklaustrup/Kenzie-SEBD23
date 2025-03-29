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
public final class ReadingGoalDao_Factory implements Factory<ReadingGoalDao> {
  private final Provider<DynamoDBMapper> mapperProvider;

  public ReadingGoalDao_Factory(Provider<DynamoDBMapper> mapperProvider) {
    this.mapperProvider = mapperProvider;
  }

  @Override
  public ReadingGoalDao get() {
    return newInstance(mapperProvider.get());
  }

  public static ReadingGoalDao_Factory create(Provider<DynamoDBMapper> mapperProvider) {
    return new ReadingGoalDao_Factory(mapperProvider);
  }

  public static ReadingGoalDao newInstance(DynamoDBMapper mapper) {
    return new ReadingGoalDao(mapper);
  }
}
