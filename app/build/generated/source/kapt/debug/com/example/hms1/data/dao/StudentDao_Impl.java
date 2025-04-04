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
import com.example.hms1.data.models.StudentEntity;
import java.lang.Class;
import java.lang.Exception;
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
public final class StudentDao_Impl implements StudentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StudentEntity> __insertionAdapterOfStudentEntity;

  private final EntityDeletionOrUpdateAdapter<StudentEntity> __deletionAdapterOfStudentEntity;

  private final EntityDeletionOrUpdateAdapter<StudentEntity> __updateAdapterOfStudentEntity;

  public StudentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudentEntity = new EntityInsertionAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `students` (`id`,`name`,`registrationNumber`,`roomNumber`,`dateOfBirth`,`block`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
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
        if (entity.getRegistrationNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRegistrationNumber());
        }
        if (entity.getRoomNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRoomNumber());
        }
        if (entity.getDateOfBirth() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDateOfBirth());
        }
        if (entity.getBlock() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBlock());
        }
      }
    };
    this.__deletionAdapterOfStudentEntity = new EntityDeletionOrUpdateAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `students` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfStudentEntity = new EntityDeletionOrUpdateAdapter<StudentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `students` SET `id` = ?,`name` = ?,`registrationNumber` = ?,`roomNumber` = ?,`dateOfBirth` = ?,`block` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StudentEntity entity) {
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
        if (entity.getRegistrationNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRegistrationNumber());
        }
        if (entity.getRoomNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRoomNumber());
        }
        if (entity.getDateOfBirth() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDateOfBirth());
        }
        if (entity.getBlock() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBlock());
        }
        if (entity.getId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getId());
        }
      }
    };
  }

  @Override
  public Object insertStudent(final StudentEntity student,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStudentEntity.insert(student);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteStudent(final StudentEntity student,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfStudentEntity.handle(student);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateStudent(final StudentEntity student,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfStudentEntity.handle(student);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StudentEntity>> getAllStudents() {
    final String _sql = "SELECT `students`.`id` AS `id`, `students`.`name` AS `name`, `students`.`registrationNumber` AS `registrationNumber`, `students`.`roomNumber` AS `roomNumber`, `students`.`dateOfBirth` AS `dateOfBirth`, `students`.`block` AS `block` FROM students";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"students"}, new Callable<List<StudentEntity>>() {
      @Override
      @NonNull
      public List<StudentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = 0;
          final int _cursorIndexOfName = 1;
          final int _cursorIndexOfRegistrationNumber = 2;
          final int _cursorIndexOfRoomNumber = 3;
          final int _cursorIndexOfDateOfBirth = 4;
          final int _cursorIndexOfBlock = 5;
          final List<StudentEntity> _result = new ArrayList<StudentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StudentEntity _item;
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
            final String _tmpRegistrationNumber;
            if (_cursor.isNull(_cursorIndexOfRegistrationNumber)) {
              _tmpRegistrationNumber = null;
            } else {
              _tmpRegistrationNumber = _cursor.getString(_cursorIndexOfRegistrationNumber);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpDateOfBirth;
            if (_cursor.isNull(_cursorIndexOfDateOfBirth)) {
              _tmpDateOfBirth = null;
            } else {
              _tmpDateOfBirth = _cursor.getString(_cursorIndexOfDateOfBirth);
            }
            final String _tmpBlock;
            if (_cursor.isNull(_cursorIndexOfBlock)) {
              _tmpBlock = null;
            } else {
              _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
            }
            _item = new StudentEntity(_tmpId,_tmpName,_tmpRegistrationNumber,_tmpRoomNumber,_tmpDateOfBirth,_tmpBlock);
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
  public Object getStudentById(final String studentId,
      final Continuation<? super StudentEntity> $completion) {
    final String _sql = "SELECT * FROM students WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<StudentEntity>() {
      @Override
      @Nullable
      public StudentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfRegistrationNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "registrationNumber");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfDateOfBirth = CursorUtil.getColumnIndexOrThrow(_cursor, "dateOfBirth");
          final int _cursorIndexOfBlock = CursorUtil.getColumnIndexOrThrow(_cursor, "block");
          final StudentEntity _result;
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
            final String _tmpRegistrationNumber;
            if (_cursor.isNull(_cursorIndexOfRegistrationNumber)) {
              _tmpRegistrationNumber = null;
            } else {
              _tmpRegistrationNumber = _cursor.getString(_cursorIndexOfRegistrationNumber);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpDateOfBirth;
            if (_cursor.isNull(_cursorIndexOfDateOfBirth)) {
              _tmpDateOfBirth = null;
            } else {
              _tmpDateOfBirth = _cursor.getString(_cursorIndexOfDateOfBirth);
            }
            final String _tmpBlock;
            if (_cursor.isNull(_cursorIndexOfBlock)) {
              _tmpBlock = null;
            } else {
              _tmpBlock = _cursor.getString(_cursorIndexOfBlock);
            }
            _result = new StudentEntity(_tmpId,_tmpName,_tmpRegistrationNumber,_tmpRoomNumber,_tmpDateOfBirth,_tmpBlock);
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
}
