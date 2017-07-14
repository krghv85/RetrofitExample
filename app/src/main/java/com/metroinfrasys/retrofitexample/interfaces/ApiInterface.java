package com.metroinfrasys.retrofitexample.interfaces;

import com.metroinfrasys.retrofitexample.model.AnswerModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Metro on 14-07-2017.
 */

public interface ApiInterface {
    @GET("answers?order=desc&sort=activity&site=stackoverflow&tagged=android")
    Call<AnswerModel> getAnswerData();
}
