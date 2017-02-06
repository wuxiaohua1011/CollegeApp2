package com.example.csaper6.collegeapp2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LogInActivity extends AppCompatActivity {
EditText userNameEditText,passwordEditText;
    Button submitButton;
    FloatingActionButton createNewAccountFloatingActionButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        wireWidget();
        Backendless.initApp(getApplicationContext(), "5D8AA47B-02C1-D7C2-FF41-3A2B7FAC6600", "F55D219C-44FC-C7CD-FFFD-D83453B97A00", "v1");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackendlessUser user = new BackendlessUser();
                Backendless.UserService.login(userNameEditText.getText().toString(), passwordEditText.getText().toString(), new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));finish();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        progressDialog.dismiss();
                        Toast.makeText(LogInActivity.this, ""+fault.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


    private void wireWidget() {
        userNameEditText = (EditText)findViewById(R.id.activity_log_in_edittext_username);
        passwordEditText = (EditText)findViewById(R.id.activity_log_in_edittext_password);
        submitButton = (Button)findViewById(R.id.activity_log_in_button_submit);
        createNewAccountFloatingActionButton = (FloatingActionButton)findViewById(R.id.activity_log_in_floatingActionButton_createNewAccount);

        tempAutoFill();
    }

    private void tempAutoFill() {
        userNameEditText.setText("try2@gmail.com");
        passwordEditText.setText("try2");
    }
}
