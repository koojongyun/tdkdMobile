package com.jongyun.tdkd.api;

import com.jongyun.tdkd.domain.LoginVO;
import com.jongyun.tdkd.domain.UserVO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginApi {
    @GET("login/{id}/{password}")
    public Call<LoginVO> getLogin(@Path("id") String id, @Path("password") String password);

    @GET("login/register/duplicate/{id}")
    public Call<String> getCheckIdDuplicated(@Path("id") String id);

    @POST("login/register")
    public Call<String> registMember(@Body UserVO userVO);
}
