package com.jongyun.tdkd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jongyun.tdkd.TDKDApplication;
import com.jongyun.tdkd.api.LoginApi;
import com.jongyun.tdkd.domain.UserVO;
import com.jongyun.tdkd.fragment.TestDialogFragment;
import com.jongyun.tdkd.history.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MemberRegisterActivity extends Activity {
    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit;
    LoginApi loginApi;
    UserVO userVO;

    private EditText id;
    private Button idDuplicateBtn;
    private EditText password;
    private EditText email;
    private EditText name;
    private EditText address;
    private EditText phoneNumber;
    private Button memberRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);
        TDKDApplication.inject(this);

        retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loginApi = retrofit.create(LoginApi.class);
        userVO = new UserVO();

        id = (EditText) findViewById(R.id.register_id_text);
        idDuplicateBtn = (Button) findViewById(R.id.id_duplicate_button);
        password = (EditText) findViewById(R.id.register_password_text);
        email = (EditText) findViewById(R.id.register_email_text);
        name = (EditText) findViewById(R.id.register_name_text);
        address = (EditText) findViewById(R.id.register_address_text);
        phoneNumber = (EditText) findViewById(R.id.register_phone_number_text);
        memberRegisterButton = (Button) findViewById(R.id.member_register_button);

        memberRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRegisterValidation()) {
                    userVO.setLoginId(id.getText().toString());
                    userVO.setPassword(password.getText().toString());
                    userVO.setName(name.getText().toString());
                    userVO.setEmail(email.getText().toString());
                    userVO.setAddress(address.getText().toString());
                    userVO.setPhoneNumber(phoneNumber.getText().toString().replaceAll("-",""));
                    loginApi.registMember(userVO).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.d("실패", t.getMessage());
                            Toast.makeText(getApplicationContext(), "네트워크 연결이 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



        idDuplicateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TestDialogFragment dialog = new TestDialogFragment();
                dialog.show(getFragmentManager(), "TestDialog");
                /*
            Call<String> dupCheck = loginApi.getCheckIdDuplicated(id.getText().toString());
            dupCheck.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()) {
                        if ("DUP".equals(response.body())) {
                            Toast.makeText(getApplicationContext(), "ID가 중복되었습니다.", Toast.LENGTH_SHORT).show();
                        } else if ("UNI".equals(response.body())) {
                            Toast.makeText(getApplicationContext(), "사용 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "다시 한번 시도해 주십시오.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("실패", t.getMessage());
                    Toast.makeText(getApplicationContext(), "네트워크 연결이 원활하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            */
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean checkRegisterValidation() {
        if (id.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "ID를 입력해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!id.getText().toString().matches("^[a-zA-Z0-9]*$")) {
            Toast.makeText(getApplicationContext(), "특수 문자 입력은 허용되지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(id.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "ID의 최소 길이는 6자리 이상입니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(id.getText().toString().length() > 12) {
            Toast.makeText(getApplicationContext(), "ID의 최대 길이는 12자리 이하입니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "사용할 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "비밀번호는 6자리 이상을 사용해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(password.getText().toString().length() > 20) {
            Toast.makeText(getApplicationContext(), "비밀번호는 20자리 이하를 사용해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(name.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "이름을 입력해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(name.getText().toString().length() > 10) {
            Toast.makeText(getApplicationContext(), "이름은 10자리 이하를 사용해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!email.getText().toString().matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z-]+)*$")) {
            Toast.makeText(getApplicationContext(), "정상적인 이메일 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(address.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "주소를 입력해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(address.getText().toString().length() > 50) {
            Toast.makeText(getApplicationContext(), "주소는 50자리 이하를 사용해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(phoneNumber.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "휴대폰 번호를 입력해 주십시오.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!phoneNumber.getText().toString().matches("^01(?:0|1[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")) {
            Toast.makeText(getApplicationContext(), "정상적인 휴대폰 번호 형식이 아닙니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
