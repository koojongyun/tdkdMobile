package com.jongyun.tdkd.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputLoginId;
    EditText inputPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLoginId = (EditText) findViewById(R.id.loginTextBox);
        inputPassword = (EditText) findViewById(R.id.passwordTextBox);
        loginButton = (Button) findViewById(R.id.loginButton);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(validationInputData()) {
                    inputLoginId.setText(inputLoginId.getText().toString().trim());
                    inputPassword.setText(inputPassword.getText().toString().trim());
                }

//                loginService.getLoginCertify(inputLoginId, inputPassword) {
//
//                }
            }
        });
    }

    private boolean validationInputData() {
        if("".equals(inputLoginId.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "ID를 입력해 주십시오.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!inputLoginId.getText().toString().equals(
                inputLoginId.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "공백은 허용되지 않습니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        if("".equals(inputPassword.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해 주십시오.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(!inputPassword.getText().toString().equals(
                inputPassword.getText().toString().trim())) {
            Toast.makeText(getApplicationContext(), "공백은 허용되지 않습니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
