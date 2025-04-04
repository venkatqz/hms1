package com.example.hms1.di;

import com.example.hms1.data.AppDatabase;
import com.example.hms1.data.dao.AdminDao;
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
public final class DatabaseModule_ProvideAdminDaoFactory implements Factory<AdminDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideAdminDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AdminDao get() {
    return provideAdminDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAdminDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAdminDaoFactory(databaseProvider);
  }

  public static AdminDao provideAdminDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAdminDao(database));
  }
}
