package com.jongyun.tdkd.service;

import com.jongyun.tdkd.TDKDApplication;
import com.jongyun.tdkd.api.LoginApi;
import com.jongyun.tdkd.domain.LoginVO;

import javax.inject.Inject;

import retrofit2.Call;

public class LoginService {
    @Inject
    LoginApi loginApi;

    public LoginService() {
        TDKDApplication.inject(this);
    }

    public Call<LoginVO> getLogin(String loginId, String password) {
        return loginApi.getLogin(loginId, password);
    }

    public Call<String> getCheckIdDuplicated(String loginId) {
        return loginApi.getCheckIdDuplicated(loginId);
    }
}
