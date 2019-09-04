package com.allen.weatherapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.test.InstrumentationRegistry
import com.allen.weatherapp.remote.ApiService
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.remote.model.cwb.WeatherForecast
import com.allen.weatherapp.ui.countyselect.CountySelectNavigator
import com.allen.weatherapp.ui.countyselect.CountySelectVM
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.internal.operators.observable.ObservableCreate
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import androidx.test.core.app.ApplicationProvider
import com.allen.weatherapp.di.*
import com.allen.weatherapp.remote.TestData
import com.allen.weatherapp.ui.countyselect.CountySelectModule
import com.google.gson.GsonBuilder
import io.mockk.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidLogger
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest : KoinTest {

    lateinit var mockWebServer : MockWebServer

    @Before
    fun before() {
        MockKAnnotations.init(this)

        stopKoin()

        startKoin {
            androidLogger()
            androidContext(ApplicationProvider.getApplicationContext<App>())
            modules(listOf(CountySelectModule, TestRemoteModule, AppModule, WeatherForecastModule))
        }

        RxJavaPlugins.setIoSchedulerHandler { t ->  Schedulers.trampoline() }

        mockWebServer = get()

        mockWebServer.start()
    }
    @After
    fun after() {
        mockWebServer.shutdown()

        stopKoin()
    }

    @Test
    fun GetWeatherForecast_Test() {
        println("start test")

        val gson = GsonBuilder().create()

        mockWebServer.url("/")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    gson.toJson(TestData.WeatherForecast_Response_Test1).toString()
                )
        )

        val navigator = spyk(object : CountySelectNavigator {
            override fun gotoWeatherForecast(record: WeatherForecast.Response.Records) {
                print("gotoWeatherForecast succeed")
            }
        })

        val countySelectVM = get<CountySelectVM> { parametersOf(navigator) }

        countySelectVM.remoteGetWeatherForecast(CWB_API_CountyCode.高雄市)

        verify {navigator.gotoWeatherForecast(any())}
    }

}
