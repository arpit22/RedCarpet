package com.example.arpit.redcarpetapp;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Arpit on 05/02/2018.
 */

public interface RetrofitApi {

    String BASE_URL = "http://www.androidbegin.com";
    @GET("tutorial/jsonparsetutorial.txt")
    Call<JSONResponse> getJsonResponse();

}
