package com.example.nalhdaf.easyenglishlearning3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail,userPassword;
    private Button newRegisterButton,userLoginButton;
    private FirebaseAuth auth;

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        auth=FirebaseAuth.getInstance();

        userEmail =(EditText)findViewById(R.id.emailEditText);
        userPassword =(EditText)findViewById(R.id.passwordEditText);
        newRegisterButton =(Button)findViewById(R.id.registerButton);
        userLoginButton =(Button)findViewById(R.id.loginButton);

        newRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                registerFunction();

            }
        });


        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void registerFunction() {

        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please enter your email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please enter your password",Toast.LENGTH_SHORT).show();
        }
        if (password.length()<6){
            Toast.makeText(getApplicationContext(),"Password must be at least 6 digits", Toast.LENGTH_SHORT).show();
        }

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authorization Error",
                                    Toast.LENGTH_SHORT).show();
                        }

                        else {
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                });
    }
}
