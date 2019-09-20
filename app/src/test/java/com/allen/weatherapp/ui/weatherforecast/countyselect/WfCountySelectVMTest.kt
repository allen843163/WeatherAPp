package com.allen.weatherapp.ui.weatherforecast.countyselect

import androidx.test.core.app.ApplicationProvider
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.TestData
import com.allen.core.remote.cwb.model.WeatherForecast
import com.allen.weatherapp.App
import com.allen.weatherapp.di.AppModule
import com.allen.weatherapp.di.TestRemoteModule
import com.allen.weatherapp.ui.weatherforecast.detail.WeatherForecastModule
import com.google.gson.GsonBuilder
import io.mockk.MockKAnnotations
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WfCountySelectVMTest : KoinTest {

    lateinit var mockWebServer : MockWebServer

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        stopKoin()

        startKoin {
            androidLogger()
            androidContext(ApplicationProvider.getApplicationContext<App>())
            modules(listOf(WfCountySelectModule, TestRemoteModule, AppModule))
        }

        RxJavaPlugins.setIoSchedulerHandler { t ->  Schedulers.trampoline() }

        mockWebServer = get()

        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()

        stopKoin()
    }

    @Test
    fun remoteGetWeatherForecast_Test() {
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

        val navigator = spyk(object : WfCountySelectNavigator {
            override fun gotoWeatherForecast(record: WeatherForecast.Response.Records) {
                print("gotoWeatherForecast succeed")
            }
        })

        val countySelectVM = get<WfCountySelectVM> { parametersOf(navigator) }

        countySelectVM.remoteGetWeatherForecast(CWB_API_CountyCode.高雄市)

        verify {navigator.gotoWeatherForecast(any())}
    }
}