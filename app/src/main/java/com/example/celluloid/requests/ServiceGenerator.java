package com.example.celluloid.requests;

import com.example.celluloid.util.Constants;
import com.example.celluloid.util.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.celluloid.util.Constants.CONNECTION_TIMEOUT;
import static com.example.celluloid.util.Constants.READ_TIMEOUT;
import static com.example.celluloid.util.Constants.WRITE_TIMEOUT;

public class ServiceGenerator {

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static TMDbApi tmDbApi = retrofit.create(TMDbApi.class);

    public static TMDbApi getTMDbApi(){ return tmDbApi; }
}
