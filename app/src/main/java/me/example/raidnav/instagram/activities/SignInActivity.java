package me.example.raidnav.instagram.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.example.raidnav.instagram.R;

public class SignInActivity extends AppCompatActivity {

//  private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

  @Override
  protected void onStart() {
    super.onStart();
//    if (firebaseAuth.getCurrentUser() != null) {
//      Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
//      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//      startActivity(intent);
//    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    setContentView(R.layout.activity_login);

    ProgressBar progressBar = findViewById(R.id.login_progress);
    EditText textEmail = findViewById(R.id.input_email);
    EditText textPassword = findViewById(R.id.input_password);
    Button loginBtn = findViewById(R.id.login_button);
    TextView createAccount = findViewById(R.id.create_account);

    progressBar.setVisibility(View.GONE);

    loginBtn.setOnClickListener(v -> {
      String email = textEmail.getText().toString();
      String password = textPassword.getText().toString();

      if (email.length() > 0 && password.length() > 0) {
        progressBar.setVisibility(View.VISIBLE);
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(SignInActivity.this, task -> {
//              if (task.isSuccessful()) {
//                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//                if (firebaseUser != null && firebaseUser.isEmailVerified()) {
//                  startActivity(new Intent(SignInActivity.this, HomeActivity.class));
//                  Toast.makeText(SignInActivity.this, "login success", Toast.LENGTH_LONG).show();
//                } else {
//                  Toast.makeText(SignInActivity.this, "login failed", Toast.LENGTH_LONG).show();
//                  progressBar.setVisibility(View.GONE);
//                  firebaseAuth.signOut();
//                }
//              } else {
//                // TODO: add log
//                progressBar.setVisibility(View.GONE);
//                Toast.makeText(SignInActivity.this, "email or password doesn't match",
//                    Toast.LENGTH_SHORT).show();
//              }
//            });

      } else {
        Toast.makeText(SignInActivity.this, "input cannot be empty", Toast.LENGTH_SHORT).show();
      }
    });

    createAccount.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));
  }
}
