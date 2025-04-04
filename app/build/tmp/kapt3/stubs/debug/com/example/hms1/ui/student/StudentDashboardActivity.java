package com.example.hms1.ui.student;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\fH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002J\b\u0010\u0016\u001a\u00020\fH\u0002J\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/hms1/ui/student/StudentDashboardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/hms1/databinding/ActivityStudentDashboardBinding;", "complaintAdapter", "Lcom/example/hms1/ui/adapters/ComplaintAdapter;", "currentUser", "Lcom/example/hms1/data/models/Student;", "db", "Lcom/google/firebase/firestore/FirebaseFirestore;", "clearForm", "", "loadComplaints", "loadUserData", "userId", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupComplaintForm", "setupComplaintsList", "showError", "message", "showSuccess", "app_debug"})
public final class StudentDashboardActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.hms1.databinding.ActivityStudentDashboardBinding binding;
    private com.example.hms1.data.models.Student currentUser;
    @org.jetbrains.annotations.NotNull()
    private final com.example.hms1.ui.adapters.ComplaintAdapter complaintAdapter = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore db = null;
    
    public StudentDashboardActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void loadUserData(java.lang.String userId) {
    }
    
    private final void setupComplaintForm() {
    }
    
    private final void setupComplaintsList() {
    }
    
    private final void loadComplaints() {
    }
    
    private final void clearForm() {
    }
    
    private final void showError(java.lang.String message) {
    }
    
    private final void showSuccess(java.lang.String message) {
    }
}