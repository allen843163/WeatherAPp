package com.allen.weatherapp

import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectNavigator
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectVM
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import androidx.test.core.app.ApplicationProvider
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.TestData
import com.allen.core.remote.cwb.model.WeatherForecast
import com.allen.weatherapp.di.*
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectModule
import com.google.gson.GsonBuilder
import io.mockk.*
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidLogger
import org.koin.core.parameter.parametersOf
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
            modules(listOf(WfCountySelectModule, TestRemoteModule, AppModule, WeatherForecastModule))
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
