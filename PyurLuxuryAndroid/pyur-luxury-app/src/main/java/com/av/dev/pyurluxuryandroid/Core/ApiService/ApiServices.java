package com.av.dev.pyurluxuryandroid.Core.ApiService;

import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponse;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostLandBookObject;
import com.av.dev.pyurluxuryandroid.Core.object.SendPost.PostSeaBookObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by CodeSyaona on 20/09/2017.
 */

public interface ApiServices {

    @Headers("Content-Type:application/json")
    @POST("pyur/transactions")
    Call<ApiResponse> landBookService(@Header("x-access-token") String token, @Body PostLandBookObject body);

    @Headers("Content-Type:application/json")
    @POST("pyur/transactions")
    Call<ApiResponse> seaBookService(@Header("x-access-token") String token, @Body PostSeaBookObject body);


}
