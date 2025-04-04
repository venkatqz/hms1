package com.example.hms1.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH\u0007J\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u0004H\u0007\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0011H\u0007J\u0010\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0007J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\tH\u0007J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\tH\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/example/hms1/data/Converters;", "", "()V", "dateToTimestamp", "", "date", "Ljava/util/Date;", "(Ljava/util/Date;)Ljava/lang/Long;", "fromComplaintStatus", "", "value", "Lcom/example/hms1/data/models/ComplaintStatus;", "fromComplaintType", "Lcom/example/hms1/data/models/ComplaintType;", "fromTimestamp", "(Ljava/lang/Long;)Ljava/util/Date;", "fromWorkerType", "Lcom/example/hms1/data/models/WorkerType;", "toComplaintStatus", "toComplaintType", "toWorkerType", "app_debug"})
public final class Converters {
    
    public Converters() {
        super();
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.util.Date fromTimestamp(@org.jetbrains.annotations.Nullable()
    java.lang.Long value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long dateToTimestamp(@org.jetbrains.annotations.Nullable()
    java.util.Date date) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromComplaintType(@org.jetbrains.annotations.NotNull()
    com.example.hms1.data.models.ComplaintType value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.hms1.data.models.ComplaintType toComplaintType(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromComplaintStatus(@org.jetbrains.annotations.NotNull()
    com.example.hms1.data.models.ComplaintStatus value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.hms1.data.models.ComplaintStatus toComplaintStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String fromWorkerType(@org.jetbrains.annotations.NotNull()
    com.example.hms1.data.models.WorkerType value) {
        return null;
    }
    
    @androidx.room.TypeConverter()
    @org.jetbrains.annotations.NotNull()
    public final com.example.hms1.data.models.WorkerType toWorkerType(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
}