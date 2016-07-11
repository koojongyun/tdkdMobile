package com.jongyun.tdkd.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginService {
    public static final String API_URL = "http://localhost:3000/";

    @GET("/login")
    Call<ResponseBody> getLogin(@Query("id") String loginId, @Query("password") String password);
}
