package com.spendwise.app.di;

import android.app.Application;
import com.spendwise.app.data.local.AppDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DatabaseModule_ProvideAppDatabaseFactory implements Factory<AppDatabase> {
  private final Provider<Application> appProvider;

  public DatabaseModule_ProvideAppDatabaseFactory(Provider<Application> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public AppDatabase get() {
    return provideAppDatabase(appProvider.get());
  }

  public static DatabaseModule_ProvideAppDatabaseFactory create(Provider<Application> appProvider) {
    return new DatabaseModule_ProvideAppDatabaseFactory(appProvider);
  }

  public static AppDatabase provideAppDatabase(Application app) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAppDatabase(app));
  }
}
