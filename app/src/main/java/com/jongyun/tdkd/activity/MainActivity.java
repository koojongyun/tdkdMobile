package com.jongyun.tdkd.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jongyun.tdkd.history.R;
import com.jongyun.tdkd.service.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    Gson gson = new GsonBuilder().setLenient().create();

    Retrofit retrofit;
    LoginService loginService;

    EditText inputLoginId;
    EditText inputPassword;
    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loginService = retrofit.create(LoginService.class);

        inputLoginId = (EditText) findViewById(R.id.loginTextBox);
        inputPassword = (EditText) findViewById(R.id.passwordTextBox);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.memberRegisterButton);
    }

    @Override
    protected void onStart() {
        super.onStart();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(validationInputData()) {
                    inputLoginId.setText(inputLoginId.getText().toString().trim());
                    inputPassword.setText(inputPassword.getText().toString().trim());

                    Call<String> loginService = MainActivity.this.loginService.getLogin(inputLoginId.getText().toString(), inputPassword.getText().toString());
                    loginService.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> loginResult, Response<String> response) {
                            Log.d("성공", "로그인이 성공하였습니다.");
                            Log.d("성공", response.body().toString());
                            Toast.makeText(getApplicationContext(), "로그인 되었습니다.", Toast.LENGTH_LONG);
                        }
                        @Override
                        public void onFailure(Call<String> call,  Throwable t) {
                            Log.d("실패", "로그인이 실패 하였습니다.");
                            Log.d("실패", t.getMessage());
                        }
                    });
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 로그인이 성공할 경우 다음 액티비티로 이동한다.
                Intent intent = new Intent(getApplicationContext(), MemberRegisterActivity.class);
                startActivity(intent);
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
