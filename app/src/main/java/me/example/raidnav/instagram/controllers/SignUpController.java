package me.example.raidnav.instagram.controllers;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import me.example.raidnav.instagram.data.User;
import me.example.raidnav.instagram.data.UserAccountSettings;

public class SignUpController {

  private static final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
  private static final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

  public static boolean canSendVerificationEmail() {
    AtomicBoolean can = new AtomicBoolean(false);
    if (firebaseAuth.getCurrentUser() != null) {
      firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(task -> {
        can.set(task.isSuccessful());
      });
    }
    return can.get();
  }

  public static boolean createUser(String username, String email, String password) {
    AtomicBoolean canCreateUser = new AtomicBoolean(false);
    firebaseAuth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(task -> task.addOnSuccessListener(authResult -> {
          Query query = dbRef
              .child("users")
              .orderByChild("username")
              .equalTo(username.replace(" ", "."));

          query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              if (!snapshot.exists()) {
                addNewUserData(email, username, username);
              } else {
                addNewUserData(email, username + "." + Objects.requireNonNull(dbRef.push().getKey()).substring(3,
                    10), username);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
          });

          canCreateUser.set(true);
        }));
    return canCreateUser.get();
  }

  private static void addNewUserData(String email, String username, String displayName) {
    User user = new User(firebaseAuth.getCurrentUser().getUid(), (long) 0, email,
        username.replace(" ", ".").toLowerCase());
    final UserAccountSettings settings = new UserAccountSettings("Your bio", displayName,
        "", username.replace(" ", ".").toLowerCase(), "Your website");

    dbRef
        .child("users")
        .child(firebaseAuth.getCurrentUser().getUid())
        .setValue(user)
        .addOnCompleteListener((OnCompleteListener<Void>) task -> dbRef.child("user_account_settings")
            .child(firebaseAuth.getCurrentUser().getUid())
            .setValue(settings)
            .addOnCompleteListener((OnCompleteListener<Void>) task1 -> {
              if (!Objects.requireNonNull(firebaseAuth.getCurrentUser()).isEmailVerified()) {
                if (SignUpController.canSendVerificationEmail()) {

                  firebaseAuth.signOut();
                }
              }
            }));

  }
}
