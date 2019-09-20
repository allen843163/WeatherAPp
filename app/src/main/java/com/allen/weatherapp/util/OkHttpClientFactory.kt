package com.allen.weatherapp.util

import android.content.Context
import android.util.Log
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object OkHttpClientFactory {
    fun getDefaultOkHttpClient() : OkHttpClient.Builder = let{
        Log.d("AllenTest","okhttpclient instance test")
        OkHttpClient.Builder()
    }

    /**
     * Reference : https://blog.csdn.net/u014653815/article/details/83754057
     */
    fun getSafeOkHttpClient(context : Context, cerResIDs : ArrayList<Int>): OkHttpClient.Builder {
        Log.d("AllenTest","okhttpclient instance test")

        val certificateFactory = CertificateFactory.getInstance("X.509")

        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())

        keyStore.load(null, null)

        cerResIDs.forEachIndexed { index, i ->
            val ca = certificateFactory.generateCertificate(context.getResources().openRawResource(i));
            keyStore.setCertificateEntry("ca" + index, ca);
        }

        val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())

        tmf.init(keyStore)

        val sslContext = SSLContext.getInstance("TLS")

        sslContext.init(null, tmf.getTrustManagers(), SecureRandom())

        return OkHttpClient.Builder()
            .sslSocketFactory(sslContext.getSocketFactory(), tmf.getTrustManagers()[0] as X509TrustManager)
            .hostnameVerifier(HostnameVerifier { hostname, session -> true})
    }

    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        Log.d("AllenTest","okhttpclient instance test")
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")

        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier(HostnameVerifier { hostname, session -> true })
    }
}