package com.av.dev.pyurluxuryandroid.Core.ApiService;

import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponsePerTransaction;
import com.av.dev.pyurluxuryandroid.Core.ApiResponse.ApiResponseTransaction;
import com.av.dev.pyurluxuryandroid.Core.object.ApiDetailsBespokeObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by CodeSyaona on 22/09/2017.
 */

public interface ApiServiceTransaction {

    @GET("pyur/transactions")
    Call<ApiResponseTransaction> getTransactions(@Header("x-access-token") String token);

    @GET("pyur/per_transaction")
    Call<ApiResponsePerTransaction> getPerTransaction(@Header("x-access-token") String token,
                                                      @Query("_id") String id);

    @GET("pyur/per_transaction")
    Call<ApiDetailsBespokeObject> getBeSpokeTransaction(@Header("x-access-token") String token,
                                                        @Query("_id") String id);


}
