package com.example.hms1.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/example/hms1/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "adminDao", "Lcom/example/hms1/data/dao/AdminDao;", "complaintDao", "Lcom/example/hms1/data/dao/ComplaintDao;", "studentDao", "Lcom/example/hms1/data/dao/StudentDao;", "workerDao", "Lcom/example/hms1/data/dao/WorkerDao;", "app_debug"})
@androidx.room.Database(entities = {com.example.hms1.data.models.StudentEntity.class, com.example.hms1.data.models.AdminEntity.class, com.example.hms1.data.models.WorkerEntity.class, com.example.hms1.data.models.Complaint.class}, version = 1)
@androidx.room.TypeConverters(value = {com.example.hms1.data.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.hms1.data.dao.StudentDao studentDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.hms1.data.dao.AdminDao adminDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.hms1.data.dao.WorkerDao workerDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.hms1.data.dao.ComplaintDao complaintDao();
}