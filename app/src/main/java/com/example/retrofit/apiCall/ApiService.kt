package com.example.retrofit.apiCall

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

    fun create() : Retrofit{

        /*                 ******** Why Interseptor Used In Retrofit ***********
        *     Interceptors are a powerful mechanism present in OkHttp that can monitor, rewrite, and retry calls.
        *                       Interceptors can be majorly divided into two categories:

        *      Application Interceptors : To register an application interceptor, we need to call addInterceptor() on OkHttpClient.Builder
        *            Network Interceptors : To register a Network Interceptor, invoke addNetworkInterceptor() instead of addInterceptor()
        *
        * */

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        val BASE_URL:String="https://jsonplaceholder.typicode.com"
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}



















