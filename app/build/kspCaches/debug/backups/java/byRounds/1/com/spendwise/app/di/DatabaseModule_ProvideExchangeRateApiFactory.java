package com.spendwise.app.di;

import com.spendwise.app.data.remote.ExchangeRateApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseModule_ProvideExchangeRateApiFactory implements Factory<ExchangeRateApi> {
  @Override
  public ExchangeRateApi get() {
    return provideExchangeRateApi();
  }

  public static DatabaseModule_ProvideExchangeRateApiFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ExchangeRateApi provideExchangeRateApi() {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExchangeRateApi());
  }

  private static final class InstanceHolder {
    private static final DatabaseModule_ProvideExchangeRateApiFactory INSTANCE = new DatabaseModule_ProvideExchangeRateApiFactory();
  }
}
