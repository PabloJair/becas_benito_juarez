package com.s10plus.core_application.network


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.s10plus.core_application.BuildConfig
import com.s10plus.core_application.S10PlusApplication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


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
                .client(UnsafeOkHttpClient.unsafeOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addConverterFactory(JaxbConverterFactory.create())
                .build()
            retrofit!!
        }

    }

    object UnsafeOkHttpClient {
        val unsafeOkHttpClient: OkHttpClient

        // Install the all-trusting trust manager

            // Create an ssl socket factory with our all-trusting manager
            get() = try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {

                        }

                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )



                // Install the all-trusting trust manager
                val sslContext =getSSLConfig(S10PlusApplication.currentApplication)

                val builder = OkHttpClient.Builder()

                builder.hostnameVerifier({ hostname, session -> true })
                builder.sslSocketFactory(
                    sslContext!!.socketFactory,
                    trustAllCerts[0] as X509TrustManager
                )
                builder
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(HttpLoggingInterceptor())
                    .connectTimeout(connectTimeoutInSeconds, TimeUnit.SECONDS)
                    .readTimeout(readTimeoutInSeconds, TimeUnit.SECONDS)
                    .followRedirects(followRedirects)
                builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
    }



    private fun getSSLConfig(context: Context): SSLContext? {

        // Loading CAs from an InputStream
        var cf: CertificateFactory? = null
        cf = CertificateFactory.getInstance("X.509")
        var ca: Certificate?=null
        var cert   =context.resources.openRawResource(com.s10plus.core_application.R.raw.cert)

        cf.generateCertificate(cert).also { ca = it }

        // Creating a KeyStore containing our trusted CAs
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType)
        keyStore.load(null, null)
        keyStore.setCertificateEntry("ca", ca)

        // Creating a TrustManager that trusts the CAs in our KeyStore.
        val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
        val tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
        tmf.init(keyStore)

        // Creating an SSLSocketFactory that uses our TrustManager
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, tmf.trustManagers, null)
        return sslContext
    }
    fun <T> getService(service: Class<T>): T {
        return getInstance().create(service)
    }

}
