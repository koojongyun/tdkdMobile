package com.jongyun.tdkd.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jongyun.tdkd.history.R;

public class MemberRegisterActivity extends Activity {
    private EditText id;
    private Button idDuplicateBtn;
    private EditText password;
    private EditText email;
    private EditText address;
    private EditText phoneNumber;
    private Button memberRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);

        id = (EditText) findViewById(R.id.register_id_text);
        idDuplicateBtn = (Button) findViewById(R.id.id_duplicate_button);
        password = (EditText) findViewById(R.id.register_password_text);
        email = (EditText) findViewById(R.id.register_email_text);
        address = (EditText) findViewById(R.id.register_address_text);
        phoneNumber = (EditText) findViewById(R.id.register_phone_number_text);
        memberRegisterButton = (Button) findViewById(R.id.member_register_button);

        memberRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkRegisterValidation()) {
                    Log.d("회원", "회원가입이 되었습니다");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private boolean checkRegisterValidation() {
        if (id.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "ID를 입력해 주십시오.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (id.getText().toString().matches("[^A-Za-z0-9]")) {
            Toast.makeText(getApplicationContext(), "특수 문자 입력은 허용되지 않습니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        if(id.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "ID의 최소 길이는 6자리 이상입니다.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
