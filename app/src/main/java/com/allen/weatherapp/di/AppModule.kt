package com.allen.weatherapp.di

import android.content.Context
import com.allen.core.remote.cwb.CWB_ApiService
import com.allen.core.remote.cwb.model.WeatherForecast
import com.allen.core.remote.epa.EPA_ApiService
import com.allen.weatherapp.MainActivity
import com.allen.weatherapp.R
import com.allen.weatherapp.ui.weatherforecast.detail.ForecastReportAdapter
import com.allen.weatherapp.ui.weatherforecast.detail.WeatherForecastVM
import com.allen.weatherapp.util.OkHttpClientFactory
import com.allen.weatherapp.util.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.parameter.DefinitionParameters
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module


val AppModule = module {
    single { GsonBuilder().create() }

    scope(named<MainActivity>()) {
        scoped { Gson() }
    }

    single { MockWebServer() }
}

val RemoteModule  = module {
    single { OkHttpClientFactory.getDefaultOkHttpClient() }

    single(named("epa")) {
        OkHttpClientFactory.getSafeOkHttpClient(get(), arrayListOf(R.raw.epa))
    }

    factory {
        RetrofitFactory.createService<CWB_ApiService>(CWB_ApiService.BASE_URL.toHttpUrlOrNull() as HttpUrl,  get())
    }

    factory {
        RetrofitFactory.createService<EPA_ApiService>(EPA_ApiService.BASE_URL.toHttpUrlOrNull() as HttpUrl, get(named("epa")))
    }
}

val TestRemoteModule = module {
    single { OkHttpClientFactory.getUnsafeOkHttpClient() }

    factory {
        RetrofitFactory.createService<CWB_ApiService>(get<MockWebServer>().url("/"), get())
    }
    factory {
        RetrofitFactory.createService<EPA_ApiService>(get<MockWebServer>().url("/"), get())
    }
}

