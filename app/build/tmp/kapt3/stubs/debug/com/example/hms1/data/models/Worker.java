package com.example.hms1.data.models;

@com.google.firebase.firestore.IgnoreExtraProperties()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\rJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\bH\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003Jc\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u00d6\u0003J\t\u0010+\u001a\u00020,H\u00d6\u0001J\t\u0010-\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0017X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0016\u0010\u0005\u001a\u00020\u00038\u0017X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006."}, d2 = {"Lcom/example/hms1/data/models/Worker;", "Lcom/example/hms1/data/models/HasPassword;", "id", "", "name", "password", "workerId", "workerType", "Lcom/example/hms1/data/WorkerType;", "category", "email", "block", "role", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/hms1/data/WorkerType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBlock", "()Ljava/lang/String;", "getCategory", "getEmail", "getId", "getName", "getPassword", "getRole", "userType", "Lcom/example/hms1/data/models/UserType;", "getUserType", "()Lcom/example/hms1/data/models/UserType;", "getWorkerId", "getWorkerType", "()Lcom/example/hms1/data/WorkerType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
public final class Worker extends com.example.hms1.data.models.HasPassword {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String password = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String workerId = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.hms1.data.WorkerType workerType = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String category = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String email = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String block = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String role = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.hms1.data.models.UserType userType = com.example.hms1.data.models.UserType.WORKER;
    
    public Worker(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String workerId, @org.jetbrains.annotations.NotNull()
    com.example.hms1.data.WorkerType workerType, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String block, @org.jetbrains.annotations.NotNull()
    java.lang.String role) {
        super();
    }
    
    @com.google.firebase.firestore.Exclude()
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getId() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @com.google.firebase.firestore.Exclude()
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getPassword() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWorkerId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hms1.data.WorkerType getWorkerType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBlock() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRole() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.hms1.data.models.UserType getUserType() {
        return null;
    }
    
    public Worker() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hms1.data.WorkerType component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.hms1.data.models.Worker copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String workerId, @org.jetbrains.annotations.NotNull()
    com.example.hms1.data.WorkerType workerType, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String block, @org.jetbrains.annotations.NotNull()
    java.lang.String role) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}