package com.allen.weatherapp.di

import android.app.Application
import android.os.Build
import com.allen.weatherapp.App
import com.allen.weatherapp.MainActivity
import com.allen.weatherapp.Variable
import com.allen.weatherapp.remote.ApiService
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.allen.weatherapp.ui.countyselect.CountySelectVM
import com.allen.weatherapp.ui.main.MainVM
import com.allen.weatherapp.ui.weatherforecast.ForecastReportAdapter
import com.allen.weatherapp.ui.weatherforecast.WeatherForecastVM
import com.allen.weatherapp.remote.ProxyRetrofitQueryMap
import com.allen.weatherapp.ui.countyselect.CountySelectNavigator
import com.allen.weatherapp.ui.countyselect.CountySelectRemote
import com.allen.weatherapp.ui.main.MainFragment
import com.allen.weatherapp.util.RetrofitFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.mockwebserver.MockWebServer
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


val AppModule = module {
    single { GsonBuilder().create() }

    scope(named<MainActivity>()) {
        scoped { Gson() }
    }

    single { MockWebServer() }
}

val RemoteModule  = module {
    factory {
        RetrofitFactory.createService<ApiService>(ApiService.BASE_URL.toHttpUrlOrNull() as HttpUrl)
    }
}

val TestRemoteModule = module {
    factory {
        RetrofitFactory.createService<ApiService>(get<MockWebServer>().url("/"))
    }
}

val ViewModelModule = module {
    viewModel { MainVM(get())}

    viewModel { WeatherForecastVM(get()) }
}

val WeatherForecastModule = module {
    factory { (reportData : WeatherForecast.Response.Records) -> ForecastReportAdapter(reportData) }
}

