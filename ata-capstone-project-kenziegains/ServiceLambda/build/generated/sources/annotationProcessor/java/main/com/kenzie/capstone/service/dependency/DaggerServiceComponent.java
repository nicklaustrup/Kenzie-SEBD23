package com.kenzie.capstone.service.dependency;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.kenzie.capstone.service.LambdaService;
import com.kenzie.capstone.service.dao.ExampleDao;
import dagger.internal.DoubleCheck;
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
public final class DaggerServiceComponent implements ServiceComponent {
  private Provider<DynamoDBMapper> provideDynamoDBMapperProvider;

  private Provider<ExampleDao> provideExampleDaoProvider;

  private Provider<LambdaService> provideLambdaServiceProvider;

  private DaggerServiceComponent(DaoModule daoModuleParam, ServiceModule serviceModuleParam) {

    initialize(daoModuleParam, serviceModuleParam);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static ServiceComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final DaoModule daoModuleParam, final ServiceModule serviceModuleParam) {
    this.provideDynamoDBMapperProvider = DoubleCheck.provider(DaoModule_ProvideDynamoDBMapperFactory.create(daoModuleParam));
    this.provideExampleDaoProvider = DoubleCheck.provider(DaoModule_ProvideExampleDaoFactory.create(daoModuleParam, provideDynamoDBMapperProvider));
    this.provideLambdaServiceProvider = DoubleCheck.provider(ServiceModule_ProvideLambdaServiceFactory.create(serviceModuleParam, provideExampleDaoProvider));
  }

  @Override
  public LambdaService provideLambdaService() {
    return provideLambdaServiceProvider.get();
  }

  public static final class Builder {
    private DaoModule daoModule;

    private ServiceModule serviceModule;

    private Builder() {
    }

    public Builder daoModule(DaoModule daoModule) {
      this.daoModule = Preconditions.checkNotNull(daoModule);
      return this;
    }

    public Builder serviceModule(ServiceModule serviceModule) {
      this.serviceModule = Preconditions.checkNotNull(serviceModule);
      return this;
    }

    public ServiceComponent build() {
      if (daoModule == null) {
        this.daoModule = new DaoModule();
      }
      if (serviceModule == null) {
        this.serviceModule = new ServiceModule();
      }
      return new DaggerServiceComponent(daoModule, serviceModule);
    }
  }
}
