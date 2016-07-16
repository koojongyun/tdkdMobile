package com.jongyun.tdkd.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoginService {

    @GET("login/{id}/{password}")
    Call<String> getLogin(@Path("id") String id, @Path("password") String password);
}
