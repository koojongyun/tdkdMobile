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
    private EditText name;
    private EditText email;
    private EditText address;
    private EditText phoneNumber;
    private Button memberRegisterButton;

    @Before
    public void setUp() throws Exception {
        subject = Robolectric.setupActivity(MemberRegisterActivity.class);

        id = (EditText) subject.findViewById(R.id.register_id_text);
        idDuplicateBtn = (Button) subject.findViewById(R.id.id_duplicate_button);
        password = (EditText) subject.findViewById(R.id.register_password_text);
        name = (EditText) subject.findViewById(R.id.register_name_text);
        email = (EditText) subject.findViewById(R.id.register_email_text);
        address = (EditText) subject.findViewById(R.id.register_address_text);
        phoneNumber = (EditText) subject.findViewById(R.id.register_phone_number_text);
        memberRegisterButton = (Button) subject.findViewById(R.id.member_register_button);
    }

    @Test
    public void whenInputLoginIdIsSpecialCharacter_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("1@3%$veg7", "password", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("특수 문자 입력은 허용되지 않습니다.");

        inputMemberRegisterTextField("1*&^543fhfl", "password", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("특수 문자 입력은 허용되지 않습니다.");
    }

    @Test
    public void whenInputNothingInIDTextColumn_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("", "password", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID를 입력해 주십시오.");

        inputMemberRegisterTextField("    ", "password", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID를 입력해 주십시오.");

    }

    @Test
    public void whenInputRestrictedLengthId_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("jame2", "password", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID의 최소 길이는 6자리 이상입니다.");

        inputMemberRegisterTextField("james053james053james053", "password", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("ID의 최대 길이는 12자리 이하입니다.");
    }

    @Test
    public void whenInputPasswordIsEmpty_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("사용할 비밀번호를 입력해 주세요.");
    }

    @Test
    public void whenInputRestricedLengthPassword_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "short", "name", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("비밀번호는 6자리 이상을 사용해 주십시오.");

        password.setText("tooLongPasswordUsetooLongPasswordUse");
        inputMemberRegisterTextField("normalId", "tooLongPasswordUsetooLongPasswordUse", "name", "normal@naver.com", "normal Address", "010-7777-6666");

        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("비밀번호는 20자리 이하를 사용해 주십시오.");
    }

    @Test
    public void whenInputNameIsEmpty_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("이름을 입력해 주십시오.");

    }

    @Test
    public void whenInputRestrictedLengthName_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "tooLongName", "normal@naver.com", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("이름은 10자리 이하를 사용해 주십시오.");
    }

    @Test
    public void whenInputAbnormalEmailAddress_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "abnormal", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("정상적인 이메일 형식이 아닙니다.");


        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "abnormal@naver", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("정상적인 이메일 형식이 아닙니다.");

        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "", "normal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("정상적인 이메일 형식이 아닙니다.");
    }

    @Test
    public void whenInputAddressEmpty_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "normal@naver.com", "", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("주소를 입력해 주십시오.");
    }

    @Test
    public void whenInputRestrictedAddressLength_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "normal@naver.com", "normal Addressnormal Addressnormal Addressnormal Addressnormal Addressnormal Addressnormal Addressnormal Addressnormal Addressnormal Address", "010-7777-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("주소는 50자리 이하를 사용해 주십시오.");
    }

    @Test
    public void whenInputPhoneNumberEmpty_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "normal@naver.com", "normal Address", "");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("휴대폰 번호를 입력해 주십시오.");
    }

    @Test
    public void whenInputAbnormalPhoneNumberFormat_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("normalId", "normalPassword", "normalName", "normal@naver.com", "normal Address", "123-2377-6666");
        memberRegisterButton.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("정상적인 휴대폰 번호 형식이 아닙니다.");
    }

    @Test
    public void whenInputDuplicatedId_thenShowToastMessage() throws Exception {
        inputMemberRegisterTextField("duplicatedId", "normalPassword", "normalName", "normal@naver.com", "normal Address", "010-7777-6666");
        idDuplicateBtn.performClick();

        assertThat(ShadowToast.getTextOfLatestToast().toString()).isEqualTo("이미 존재하는 ID입니다.");
    }

    private void inputMemberRegisterTextField(String idPram, String passwordParam, String nameParam, String emailParam, String addressParam, String phoneNumberParam) {
        id.setText(idPram);
        password.setText(passwordParam);
        name.setText(nameParam);
        email.setText(emailParam);
        address.setText(addressParam);
        phoneNumber.setText(phoneNumberParam);
    }
}