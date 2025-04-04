package com.example.hms1.data.models;

@com.google.firebase.firestore.IgnoreExtraProperties()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u00048gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u00048gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0012\u0010\u000b\u001a\u00020\fX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/example/hms1/data/models/HasPassword;", "Ljava/io/Serializable;", "()V", "id", "", "getId", "()Ljava/lang/String;", "name", "getName", "password", "getPassword", "userType", "Lcom/example/hms1/data/models/UserType;", "getUserType", "()Lcom/example/hms1/data/models/UserType;", "app_debug"})
public abstract class HasPassword implements java.io.Serializable {
    
    public HasPassword() {
        super();
    }
    
    @com.google.firebase.firestore.Exclude()
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getId();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getName();
    
    @com.google.firebase.firestore.Exclude()
    @org.jetbrains.annotations.NotNull()
    public abstract java.lang.String getPassword();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.hms1.data.models.UserType getUserType();
}