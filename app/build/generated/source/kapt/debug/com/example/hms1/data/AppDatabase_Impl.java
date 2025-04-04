package com.example.hms1.data;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.hms1.data.dao.AdminDao;
import com.example.hms1.data.dao.AdminDao_Impl;
import com.example.hms1.data.dao.ComplaintDao;
import com.example.hms1.data.dao.ComplaintDao_Impl;
import com.example.hms1.data.dao.StudentDao;
import com.example.hms1.data.dao.StudentDao_Impl;
import com.example.hms1.data.dao.WorkerDao;
import com.example.hms1.data.dao.WorkerDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile StudentDao _studentDao;

  private volatile AdminDao _adminDao;

  private volatile WorkerDao _workerDao;

  private volatile ComplaintDao _complaintDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `students` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `registrationNumber` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `dateOfBirth` TEXT NOT NULL, `block` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `admins` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `block` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `workers` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `workerId` TEXT NOT NULL, `workerType` TEXT NOT NULL, `category` TEXT NOT NULL, `block` TEXT NOT NULL, `role` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `complaints` (`id` TEXT NOT NULL, `studentId` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `block` TEXT NOT NULL, `type` TEXT NOT NULL, `description` TEXT NOT NULL, `status` TEXT NOT NULL, `notes` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7b963c93a197017be5e9a42e52f7ef15')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `students`");
        db.execSQL("DROP TABLE IF EXISTS `admins`");
        db.execSQL("DROP TABLE IF EXISTS `workers`");
        db.execSQL("DROP TABLE IF EXISTS `complaints`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsStudents = new HashMap<String, TableInfo.Column>(6);
        _columnsStudents.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudents.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudents.put("registrationNumber", new TableInfo.Column("registrationNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudents.put("roomNumber", new TableInfo.Column("roomNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudents.put("dateOfBirth", new TableInfo.Column("dateOfBirth", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudents.put("block", new TableInfo.Column("block", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudents = new TableInfo("students", _columnsStudents, _foreignKeysStudents, _indicesStudents);
        final TableInfo _existingStudents = TableInfo.read(db, "students");
        if (!_infoStudents.equals(_existingStudents)) {
          return new RoomOpenHelper.ValidationResult(false, "students(com.example.hms1.data.models.StudentEntity).\n"
                  + " Expected:\n" + _infoStudents + "\n"
                  + " Found:\n" + _existingStudents);
        }
        final HashMap<String, TableInfo.Column> _columnsAdmins = new HashMap<String, TableInfo.Column>(4);
        _columnsAdmins.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAdmins.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAdmins.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAdmins.put("block", new TableInfo.Column("block", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAdmins = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAdmins = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAdmins = new TableInfo("admins", _columnsAdmins, _foreignKeysAdmins, _indicesAdmins);
        final TableInfo _existingAdmins = TableInfo.read(db, "admins");
        if (!_infoAdmins.equals(_existingAdmins)) {
          return new RoomOpenHelper.ValidationResult(false, "admins(com.example.hms1.data.models.AdminEntity).\n"
                  + " Expected:\n" + _infoAdmins + "\n"
                  + " Found:\n" + _existingAdmins);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkers = new HashMap<String, TableInfo.Column>(7);
        _columnsWorkers.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkers.put("workerId", new TableInfo.Column("workerId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkers.put("workerType", new TableInfo.Column("workerType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkers.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkers.put("block", new TableInfo.Column("block", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkers.put("role", new TableInfo.Column("role", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkers = new TableInfo("workers", _columnsWorkers, _foreignKeysWorkers, _indicesWorkers);
        final TableInfo _existingWorkers = TableInfo.read(db, "workers");
        if (!_infoWorkers.equals(_existingWorkers)) {
          return new RoomOpenHelper.ValidationResult(false, "workers(com.example.hms1.data.models.WorkerEntity).\n"
                  + " Expected:\n" + _infoWorkers + "\n"
                  + " Found:\n" + _existingWorkers);
        }
        final HashMap<String, TableInfo.Column> _columnsComplaints = new HashMap<String, TableInfo.Column>(10);
        _columnsComplaints.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("studentId", new TableInfo.Column("studentId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("roomNumber", new TableInfo.Column("roomNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("block", new TableInfo.Column("block", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComplaints = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComplaints = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComplaints = new TableInfo("complaints", _columnsComplaints, _foreignKeysComplaints, _indicesComplaints);
        final TableInfo _existingComplaints = TableInfo.read(db, "complaints");
        if (!_infoComplaints.equals(_existingComplaints)) {
          return new RoomOpenHelper.ValidationResult(false, "complaints(com.example.hms1.data.models.Complaint).\n"
                  + " Expected:\n" + _infoComplaints + "\n"
                  + " Found:\n" + _existingComplaints);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7b963c93a197017be5e9a42e52f7ef15", "efb19c74e15547217428a602207cbf76");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "students","admins","workers","complaints");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `students`");
      _db.execSQL("DELETE FROM `admins`");
      _db.execSQL("DELETE FROM `workers`");
      _db.execSQL("DELETE FROM `complaints`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(StudentDao.class, StudentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AdminDao.class, AdminDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkerDao.class, WorkerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ComplaintDao.class, ComplaintDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public StudentDao studentDao() {
    if (_studentDao != null) {
      return _studentDao;
    } else {
      synchronized(this) {
        if(_studentDao == null) {
          _studentDao = new StudentDao_Impl(this);
        }
        return _studentDao;
      }
    }
  }

  @Override
  public AdminDao adminDao() {
    if (_adminDao != null) {
      return _adminDao;
    } else {
      synchronized(this) {
        if(_adminDao == null) {
          _adminDao = new AdminDao_Impl(this);
        }
        return _adminDao;
      }
    }
  }

  @Override
  public WorkerDao workerDao() {
    if (_workerDao != null) {
      return _workerDao;
    } else {
      synchronized(this) {
        if(_workerDao == null) {
          _workerDao = new WorkerDao_Impl(this);
        }
        return _workerDao;
      }
    }
  }

  @Override
  public ComplaintDao complaintDao() {
    if (_complaintDao != null) {
      return _complaintDao;
    } else {
      synchronized(this) {
        if(_complaintDao == null) {
          _complaintDao = new ComplaintDao_Impl(this);
        }
        return _complaintDao;
      }
    }
  }
}
