package com.example.hms1.di;

import com.example.hms1.data.AppDatabase;
import com.example.hms1.data.dao.WorkerDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DatabaseModule_ProvideWorkerDaoFactory implements Factory<WorkerDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideWorkerDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public WorkerDao get() {
    return provideWorkerDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideWorkerDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideWorkerDaoFactory(databaseProvider);
  }

  public static WorkerDao provideWorkerDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWorkerDao(database));
  }
}
