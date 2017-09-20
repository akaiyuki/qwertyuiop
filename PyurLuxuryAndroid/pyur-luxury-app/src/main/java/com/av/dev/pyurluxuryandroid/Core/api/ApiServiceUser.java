package com.av.dev.pyurluxuryandroid.Core.api;

import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponseLogin;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.ApiUserLoginObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostCinemaBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostConcertBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostHotelBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostResBookObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by CodeSyaona on 18/09/2017.
 */

public interface ApiServiceUser {

    //hotel transaction
    @Headers("Content-Type:application/json")
    @POST("pyur/transactions")
    Call<ApiResponse> bookService(@Header("x-access-token") String token, @Body PostHotelBookObject body);

    //res transaction
    @Headers("Content-Type:application/json")
    @POST("pyur/transactions")
    Call<ApiResponse> resBookService(@Header("x-access-token") String token, @Body PostResBookObject body);

    //cinema transaction
    @Headers("Content-Type:application/json")
    @POST("pyur/transactions")
    Call<ApiResponse> cinemaBookService(@Header("x-access-token") String token, @Body PostCinemaBookObject body);

    //concert ticketing
    @Headers("Content-Type:application/json")
    @POST("pyur/transactions")
    Call<ApiResponse> concertBookService(@Header("x-access-token") String token, @Body PostConcertBookObject body);


    //login user
    @Headers("Content-Type:application/json")
    @POST("pyur/login")
    Call<ApiResponseLogin> loginUser(@Body ApiUserLoginObject body);




}
