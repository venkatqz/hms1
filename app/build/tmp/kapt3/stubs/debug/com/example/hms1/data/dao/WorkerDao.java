package com.example.hms1.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0010\u00c0\u0006\u0001"}, d2 = {"Lcom/example/hms1/data/dao/WorkerDao;", "", "deleteWorker", "", "worker", "Lcom/example/hms1/data/models/WorkerEntity;", "(Lcom/example/hms1/data/models/WorkerEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllWorkers", "Lkotlinx/coroutines/flow/Flow;", "", "getWorkerById", "workerId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertWorker", "updateWorker", "app_debug"})
@androidx.room.Dao()
public abstract interface WorkerDao {
    
    @androidx.room.Query(value = "SELECT * FROM workers")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.hms1.data.models.WorkerEntity>> getAllWorkers();
    
    @androidx.room.Query(value = "SELECT * FROM workers WHERE id = :workerId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getWorkerById(@org.jetbrains.annotations.NotNull()
    java.lang.String workerId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.hms1.data.models.WorkerEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertWorker(@org.jetbrains.annotations.NotNull()
    com.example.hms1.data.models.WorkerEntity worker, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateWorker(@org.jetbrains.annotations.NotNull()
    com.example.hms1.data.models.WorkerEntity worker, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteWorker(@org.jetbrains.annotations.NotNull()
    com.example.hms1.data.models.WorkerEntity worker, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}