package me.example.raidnav.instagram.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.annotations.NotNull;

import java.util.regex.Pattern;

import me.example.raidnav.instagram.R;
import me.example.raidnav.instagram.controllers.SignUpController;

public class SignUpActivity extends AppCompatActivity {

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    setContentView(R.layout.activity_register);

    ProgressBar progressBar = findViewById(R.id.register_progress);
    EditText usernameText = findViewById(R.id.input_userName);
    EditText emailText = findViewById(R.id.input_email);
    EditText passwordText = findViewById(R.id.input_password);
    EditText confirmPasswordText = findViewById(R.id.input_confirm_password);
    Button signUp = findViewById(R.id.register_button);
    progressBar.setVisibility(View.GONE);

    signUp.setOnClickListener(v -> {
      String username = usernameText.getText().toString();
      String email = emailText.getText().toString();
      String password = passwordText.getText().toString();
      String confirmPassword = confirmPasswordText.getText().toString();

      if (username.length() > 0 &&
          android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
          isValidPasswordCombination(password, confirmPassword)) {
        progressBar.setVisibility(View.VISIBLE);
        boolean success = SignUpController.createUser(username, email, password);
        if (!success) {
          progressBar.setVisibility(View.GONE);
          Toast.makeText(SignUpActivity.this, "authentication failed",
              Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  private boolean isValidPasswordCombination(@NotNull String password, String confirmPassword) {
    Pattern strongPattern = Pattern.compile("^.*(?=.{7,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$");
    return password.length() > 7 && password.equals(confirmPassword) && strongPattern.matcher(password).matches();
  }
}
