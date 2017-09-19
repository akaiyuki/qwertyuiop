package com.av.dev.pyurluxuryandroid.Core.api;

import android.util.Log;

import com.av.dev.pyurluxuryandroid.Core.PConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by CodeSyaona on 18/09/2017.
 */

public class RestClient {
    private Retrofit retrofit;

    // Api Service Classes String
    public static String loginApiResponse = "ApiResponseLogin.class";
    public static String apiBookResponse = "ApiResponse.class";

    // Api Selected Service
    private ApiServiceUser apiResponse;


    /* initialize Rest Client Api */
    public RestClient(String className)
    {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(PConfiguration.apiUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        getApiRestService(className);

    }



    public ApiServiceUser getApiServiceLogin()
    {
        return apiResponse;
    }


    /* checker for which api response to use */
    private void getApiRestService(String apiServiceSelected){
        if (apiServiceSelected.equalsIgnoreCase(loginApiResponse)){
            apiResponse = retrofit.create(ApiServiceUser.class);
        }
    }




}

