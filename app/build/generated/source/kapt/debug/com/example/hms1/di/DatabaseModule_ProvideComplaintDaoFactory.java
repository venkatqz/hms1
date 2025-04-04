package com.example.hms1.di;

import com.example.hms1.data.AppDatabase;
import com.example.hms1.data.dao.ComplaintDao;
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
public final class DatabaseModule_ProvideComplaintDaoFactory implements Factory<ComplaintDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideComplaintDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ComplaintDao get() {
    return provideComplaintDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideComplaintDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideComplaintDaoFactory(databaseProvider);
  }

  public static ComplaintDao provideComplaintDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideComplaintDao(database));
  }
}
