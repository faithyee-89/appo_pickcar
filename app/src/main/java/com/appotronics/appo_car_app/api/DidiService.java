package com.appotronics.appo_car_app.api;


import com.appotronics.appo_car_app.bean.CallCarResult;
import com.appotronics.appo_car_app.bean.CommonResult;
import com.appotronics.appo_car_app.bean.LoginResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface DidiService {

    @POST("login/login")
    @FormUrlEncoded
    Call<LoginResult> login(
        @Field("phone") String phone,
        @Field("type") String type,
        @Field("opt") String opt
    );

    @POST("driver/call_car")
    @FormUrlEncoded
    Call<CallCarResult> callCar(
            @Header("token") String token,
            @Field("start_address") String startAddr,
            @Field("end_address") String endAddr,
            @Field("opt") String opt
    );

    @POST("driver/pick_order")
    @FormUrlEncoded
    Call<CommonResult> pickOrder(
            @Header("token") String token,
            @Field("order_id") String order_id,
            @Field("opt") String opt
    );

    @POST("driver/arrive_start")
    @FormUrlEncoded
    Call<CommonResult> arriveStart(
            @Header("token") String token,
            @Field("order_id") String order_id,
            @Field("opt") String opt
    );

    @POST("driver/get_on_car")
    @FormUrlEncoded
    Call<CommonResult> getOnCar(
            @Header("token") String token,
            @Field("order_id") String order_id,
            @Field("opt") String opt
    );

    @POST("driver/arrive_end")
    @FormUrlEncoded
    Call<CommonResult> arriveEnd(
            @Header("token") String token,
            @Field("order_id") String order_id,
            @Field("opt") String opt
    );

    @POST("driver/pay_order")
    @FormUrlEncoded
    Call<CommonResult> payOrder(
            @Header("token") String token,
            @Field("order_id") String order_id,
            @Field("opt") String opt
    );

    @POST("driver/send_media_temp")
    @FormUrlEncoded
    Call<CommonResult> sendMediaTemp(
            @Field("gsn") String gsn,
            @Field("file_name") String file_name
    );


}
