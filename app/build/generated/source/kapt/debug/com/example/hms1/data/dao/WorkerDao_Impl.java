package com.example.hms1.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.hms1.data.WorkerType;
import com.example.hms1.data.models.WorkerEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class WorkerDao_Impl implements WorkerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkerEntity> __insertionAdapterOfWorkerEntity;

  private final EntityDeletionOrUpdateAdapter<WorkerEntity> __deletionAdapterOfWorkerEntity;

  private final EntityDeletionOrUpdateAdapter<WorkerEntity> __updateAdapterOfWorkerEntity;

  public WorkerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkerEntity = new EntityInsertionAdapter<WorkerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `workers` (`id`,`name`,`workerId`,`workerType`,`category`,`block`,`role`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkerEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getWorkerId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getWorkerId());
        }
        statement.bindString(4, __WorkerType_enumToString(entity.getWorkerType()));
        if (entity.getCategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategory());
        }
        if (entity.getBlock() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBlock());
        }
        if (entity.getRole() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRole());
        }
      }
    };
    this.__deletionAdapterOfWorkerEntity = new EntityDeletionOrUpdateAdapter<WorkerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `workers` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkerEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfWorkerEntity = new EntityDeletionOrUpdateAdapter<WorkerEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `workers` SET `id` = ?,`name` = ?,`workerId` = ?,`workerType` = ?,`category` = ?,`block` = ?,`role` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WorkerEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getWorkerId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getWorkerId());
        }
        statement.bindString(4, __WorkerType_enumToString(entity.getWorkerType()));
        if (entity.getCategory() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCategory());
        }
        if (entity.getBlock() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBlock());
        }
        if (entity.getRole() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRole());
        }
        if (entity.getId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertWorker(final WorkerEntity worker,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfWorkerEntity.insert(worker);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteWorker(final WorkerEntity worker,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfWorkerEntity.handle(worker);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateWorker(final WorkerEntity worker,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfWorkerEntity.handle(worker);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<WorkerEntity>> getAllWorkers() {
    final String _sql = "SELECT `workers`.`id` AS `id`, `workers`.`name` AS `name`, `workers`.`workerId` AS `workerId`, `workers`.`workerType` AS `workerType`, `workers`.`category` AS `category`, `workers`.`block` AS `block`, `workers`.`role` AS `role` FROM workers";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"workers"}, new Callable<List<WorkerEntity>>() {
      @Override
      @NonNull
      public List<WorkerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfName = 1;
          final int _cursorIndexOfWorkerId = 2;
          final int _cursorIndexOfWorkerType = 3;
          final int _cursorIndexOfCategory = 4;
          final int _cursorIndexOfBlock = 5;
          final int _cursorIndexOfRole = 6;
          final List<WorkerEntity> _result = new ArrayList<WorkerEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WorkerEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpWorkerId;
            if (_cursor.isNull(_cursorIndexOfWorkerId)) {
              _tmpWorkerId = null;
            } else {
              _tmpWorkerId = _cursor.getString(_cursorIndexOfWorkerId);
            }
            final WorkerType _tmpWorkerType;
            _tmpWorkerType = __WorkerType_stringToEnum(_cursor.getString(_cursorIndexOfWorkerType));
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpBlock;
            if (_cursor.isNull(_cursorIndexOfBlock)) {
              _tmpBlock = null;
            } else {
              _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
            }
            final String _tmpRole;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmpRole = null;
            } else {
              _tmpRole = _cursor.getString(_cursorIndexOfRole);
            }
            _item = new WorkerEntity(_tmpId,_tmpName,_tmpWorkerId,_tmpWorkerType,_tmpCategory,_tmpBlock,_tmpRole);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWorkerById(final String workerId,
      final Continuation<? super WorkerEntity> $completion) {
    final String _sql = "SELECT * FROM workers WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (workerId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, workerId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WorkerEntity>() {
      @Override
      @Nullable
      public WorkerEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfWorkerId = CursorUtil.getColumnIndexOrThrow(_cursor, "workerId");
          final int _cursorIndexOfWorkerType = CursorUtil.getColumnIndexOrThrow(_cursor, "workerType");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "block");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final WorkerEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpWorkerId;
            if (_cursor.isNull(_cursorIndexOfWorkerId)) {
              _tmpWorkerId = null;
            } else {
              _tmpWorkerId = _cursor.getString(_cursorIndexOfWorkerId);
            }
            final WorkerType _tmpWorkerType;
            _tmpWorkerType = __WorkerType_stringToEnum(_cursor.getString(_cursorIndexOfWorkerType));
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpBlock;
            if (_cursor.isNull(_cursorIndexOfBlock)) {
              _tmpBlock = null;
            } else {
              _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
            }
            final String _tmpRole;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmpRole = null;
            } else {
              _tmpRole = _cursor.getString(_cursorIndexOfRole);
            }
            _result = new WorkerEntity(_tmpId,_tmpName,_tmpWorkerId,_tmpWorkerType,_tmpCategory,_tmpBlock,_tmpRole);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __WorkerType_enumToString(@NonNull final WorkerType _value) {
    switch (_value) {
      case HOUSEKEEPING: return "HOUSEKEEPING";
      case SECURITY: return "SECURITY";
      case MAINTENANCE: return "MAINTENANCE";
      case OTHER: return "OTHER";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private WorkerType __WorkerType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "HOUSEKEEPING": return WorkerType.HOUSEKEEPING;
      case "SECURITY": return WorkerType.SECURITY;
      case "MAINTENANCE": return WorkerType.MAINTENANCE;
      case "OTHER": return WorkerType.OTHER;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
