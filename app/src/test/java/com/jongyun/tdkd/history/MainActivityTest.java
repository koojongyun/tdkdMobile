package com.jongyun.tdkd.history;

import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTest {
    MainActivity subject;
    EditText loginId;
    EditText password;
    Button loginButton;

    @Before
    public void setUp() throws Exception {
        subject = Robolectric.setupActivity(MainActivity.class);

        loginId = (EditText) subject.findViewById(R.id.loginTextBox);
        password = (EditText) subject.findViewById(R.id.passwordTextBox);
        loginButton = (Button) subject.findViewById(R.id.loginButton);
    }

    @Test
    public void whenClickLoginButton_inputLoginIdAndPassword() {
        loginId.setText("james053");
        password.setText("1234qwer");
        loginButton.performClick();

        assertThat(loginId.getText().toString()).isEqualTo("james053");
        assertThat(password.getText().toString()).isEqualTo("1234qwer");
    }

    @Test
    public void whenInputLoginIdSpace_thenShowToastMessage() throws Exception {
        loginId.setText("    ");
        loginButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("ID를 입력해 주십시오.");
    }

    @Test
    public void whenInputPasswordSpace_thenShowToastMessage() throws Exception {
        loginId.setText("james053");
        password.setText("    ");
        loginButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("비밀번호를 입력해 주십시오.");
    }

    @Test
    public void whenInputLoginIdWithSpace_thenShowToastMessage() throws Exception {
        loginId.setText("     james053");
        loginButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("공백은 허용되지 않습니다.");
    }

    @Test
    public void whenInputPasswordWithSpace_thenShowToastMessage() throws Exception {
        loginId.setText("james053");
        password.setText("1234qwer    ");

        loginButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("공백은 허용되지 않습니다.");
    }

    @Test
    public void whenInputIncorrectLoginIdAndPassword_thenShowToastMessage() throws Exception {
        loginId.setText("incorrectId");
        password.setText("incorrectPassword");

        loginButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast()).isEqualTo("ID와 Password가 일치 하지 않습니다.");
    }
}