package com.jongyun.tdkd.activity;

import android.widget.Button;
import android.widget.EditText;

import com.jongyun.tdkd.history.BuildConfig;
import com.jongyun.tdkd.history.R;

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
public class MemberRegisterActivityTest {
    MemberRegisterActivity subject;
    private EditText id;
    private Button idDuplicateBtn;
    private EditText password;
    private EditText email;
    private EditText address;
    private EditText phoneNumber;
    private Button memberRegisterButton;

    @Before
    public void setUp() throws Exception {
        subject = Robolectric.setupActivity(MemberRegisterActivity.class);

        id = (EditText) subject.findViewById(R.id.register_id_text);
        idDuplicateBtn = (Button) subject.findViewById(R.id.id_duplicate_button);
        email = (EditText) subject.findViewById(R.id.register_id_text);
        address = (EditText) subject.findViewById(R.id.register_id_text);
        phoneNumber = (EditText) subject.findViewById(R.id.register_id_text);
        memberRegisterButton = (Button) subject.findViewById(R.id.member_register_button);
    }

    @Test
    public void whenInputLoginIdIsSpecialCharacter_thenShowToastMessage() throws Exception {
        id.setText("1@3%$veg7");
        memberRegisterButton.performClick();
        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("특수 문자 입력은 허용되지 않습니다.");

        id.setText("1*&^543fhfl");
        memberRegisterButton.performClick();
        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("특수 문자 입력은 허용되지 않습니다.");
    }

    @Test
    public void whenInputNothingInIDTextColumn_thenShowToastMessage() throws Exception {
        id.setText("");
        memberRegisterButton.performClick();
        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID를 입력해 주십시오.");

        id.setText("    ");
        memberRegisterButton.performClick();
        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID를 입력해 주십시오.");

    }

    @Test
    public void whenInputShortLengthId_thenShowToastMessage() throws Exception {
        id.setText("james");
        memberRegisterButton.performClick();
        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID의 최소 길이는 6자리 이상입니다.");
    }
}