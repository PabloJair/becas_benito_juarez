package com.s10plus.core_application.network


import com.google.gson.Gson
import com.s10plus.core_application.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object  ClientRetrofit {
    const val headerContentType = "Content-Type: application/json"


    private var level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
    private var retrofit: Retrofit? = null

    private const val connectTimeoutInSeconds = 60L
    private const val readTimeoutInSeconds = 60L
    private const val followRedirects = false
    private val loggingInterceptor: HttpLoggingInterceptor
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = level
            return logging
        }

    private var urlBase: String = BuildConfig.URL_BASE_API


    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            return   OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(logging)
                .connectTimeout(connectTimeoutInSeconds, TimeUnit.SECONDS)
                .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
                .followRedirects(followRedirects)
        }

    private fun getInstance():Retrofit {
        return if(retrofit!=null){
            retrofit!!
        } else {
            this.retrofit = Retrofit.Builder()
                .baseUrl(urlBase)
                .client(okHttpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addConverterFactory(JaxbConverterFactory.create())
                .build()
            retrofit!!
        }

    }


    fun <T> getService(service: Class<T>): T {
        return getInstance().create(service)
    }

}
