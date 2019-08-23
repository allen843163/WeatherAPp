package com.allen.weatherapp.di

import com.allen.weatherapp.MainActivity
import com.allen.weatherapp.remote.ApiService
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.allen.weatherapp.ui.countyselect.CountySelectVM
import com.allen.weatherapp.ui.main.MainVM
import com.allen.weatherapp.ui.weatherforecast.ForecastReportAdapter
import com.allen.weatherapp.ui.weatherforecast.WeatherForecastVM
import com.allen.weatherapp.remote.ProxyRetrofitQueryMap
import com.allen.weatherapp.ui.main.MainFragment
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val AppModule = module {
    single { Gson()}
    scope(named<MainActivity>()) {
        scoped { Gson() }
    }
}

val RemoteModule  = module {
    single<Retrofit> { (baseUrl : String) ->
        Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    }

    single<ApiService> { (baseUrl : String) ->
        get<Retrofit> {
            parametersOf(baseUrl)
        }.create(ApiService::class.java)
    }

    factory { WeatherForecast.Request() }

    factory { (requestObject : Any) ->
         RequestBody.create(MediaType.parse("application/json; charset=utf-8"), get<Gson>().toJson(requestObject))
    }

    factory { (requestObject : Any) ->
        get<Gson>().fromJson(get<Gson>().toJson(requestObject), Map::class.java)
    }

    factory { (requestObject : Any) ->
        ProxyRetrofitQueryMap(get { parametersOf(requestObject) })
    }
}

val ViewModelModule = module {
    viewModel { MainVM(get()) }

    viewModel { CountySelectVM(get()) }

    viewModel { WeatherForecastVM(get()) }
}

val WeatherForecastModule = module {
    factory { (reportData : WeatherForecast.Response.Records) -> ForecastReportAdapter(reportData) }
}

