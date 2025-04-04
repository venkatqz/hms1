// Generated by view binder compiler. Do not edit!
package com.example.hms1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.hms1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityStudentDashboardBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final MaterialButton btnRaiseComplaint;

  @NonNull
  public final MaterialButton btnSignOut;

  @NonNull
  public final MaterialButton btnSubmitComplaint;

  @NonNull
  public final MaterialButton btnViewAnnouncements;

  @NonNull
  public final MaterialButton btnViewComplaints;

  @NonNull
  public final MaterialButton btnViewProfile;

  @NonNull
  public final MaterialCardView cardComplaintForm;

  @NonNull
  public final MaterialCardView cardComplaintsList;

  @NonNull
  public final TextInputEditText etComplaintDescription;

  @NonNull
  public final RecyclerView rvComplaints;

  @NonNull
  public final TextInputLayout spinnerComplaintType;

  @NonNull
  public final Toolbar toolbar;

  private ActivityStudentDashboardBinding(@NonNull CoordinatorLayout rootView,
      @NonNull MaterialButton btnRaiseComplaint, @NonNull MaterialButton btnSignOut,
      @NonNull MaterialButton btnSubmitComplaint, @NonNull MaterialButton btnViewAnnouncements,
      @NonNull MaterialButton btnViewComplaints, @NonNull MaterialButton btnViewProfile,
      @NonNull MaterialCardView cardComplaintForm, @NonNull MaterialCardView cardComplaintsList,
      @NonNull TextInputEditText etComplaintDescription, @NonNull RecyclerView rvComplaints,
      @NonNull TextInputLayout spinnerComplaintType, @NonNull Toolbar toolbar) {
    this.rootView = rootView;
    this.btnRaiseComplaint = btnRaiseComplaint;
    this.btnSignOut = btnSignOut;
    this.btnSubmitComplaint = btnSubmitComplaint;
    this.btnViewAnnouncements = btnViewAnnouncements;
    this.btnViewComplaints = btnViewComplaints;
    this.btnViewProfile = btnViewProfile;
    this.cardComplaintForm = cardComplaintForm;
    this.cardComplaintsList = cardComplaintsList;
    this.etComplaintDescription = etComplaintDescription;
    this.rvComplaints = rvComplaints;
    this.spinnerComplaintType = spinnerComplaintType;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityStudentDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityStudentDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_student_dashboard, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityStudentDashboardBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnRaiseComplaint;
      MaterialButton btnRaiseComplaint = ViewBindings.findChildViewById(rootView, id);
      if (btnRaiseComplaint == null) {
        break missingId;
      }

      id = R.id.btnSignOut;
      MaterialButton btnSignOut = ViewBindings.findChildViewById(rootView, id);
      if (btnSignOut == null) {
        break missingId;
      }

      id = R.id.btnSubmitComplaint;
      MaterialButton btnSubmitComplaint = ViewBindings.findChildViewById(rootView, id);
      if (btnSubmitComplaint == null) {
        break missingId;
      }

      id = R.id.btnViewAnnouncements;
      MaterialButton btnViewAnnouncements = ViewBindings.findChildViewById(rootView, id);
      if (btnViewAnnouncements == null) {
        break missingId;
      }

      id = R.id.btnViewComplaints;
      MaterialButton btnViewComplaints = ViewBindings.findChildViewById(rootView, id);
      if (btnViewComplaints == null) {
        break missingId;
      }

      id = R.id.btnViewProfile;
      MaterialButton btnViewProfile = ViewBindings.findChildViewById(rootView, id);
      if (btnViewProfile == null) {
        break missingId;
      }

      id = R.id.cardComplaintForm;
      MaterialCardView cardComplaintForm = ViewBindings.findChildViewById(rootView, id);
      if (cardComplaintForm == null) {
        break missingId;
      }

      id = R.id.cardComplaintsList;
      MaterialCardView cardComplaintsList = ViewBindings.findChildViewById(rootView, id);
      if (cardComplaintsList == null) {
        break missingId;
      }

      id = R.id.etComplaintDescription;
      TextInputEditText etComplaintDescription = ViewBindings.findChildViewById(rootView, id);
      if (etComplaintDescription == null) {
        break missingId;
      }

      id = R.id.rvComplaints;
      RecyclerView rvComplaints = ViewBindings.findChildViewById(rootView, id);
      if (rvComplaints == null) {
        break missingId;
      }

      id = R.id.spinnerComplaintType;
      TextInputLayout spinnerComplaintType = ViewBindings.findChildViewById(rootView, id);
      if (spinnerComplaintType == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      return new ActivityStudentDashboardBinding((CoordinatorLayout) rootView, btnRaiseComplaint,
          btnSignOut, btnSubmitComplaint, btnViewAnnouncements, btnViewComplaints, btnViewProfile,
          cardComplaintForm, cardComplaintsList, etComplaintDescription, rvComplaints,
          spinnerComplaintType, toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
