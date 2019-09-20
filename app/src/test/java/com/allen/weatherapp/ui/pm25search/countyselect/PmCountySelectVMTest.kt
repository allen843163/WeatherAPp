package com.allen.weatherapp.ui.pm25search.countyselect

import androidx.test.core.app.ApplicationProvider
import com.allen.core.remote.cwb.CWB_API_CountyCode
import com.allen.core.remote.cwb.model.TestData
import com.allen.core.remote.cwb.model.WeatherForecast
import com.allen.core.remote.epa.EPA_API_CountyCode
import com.allen.core.remote.epa.model.EPA_TestData
import com.allen.core.remote.epa.model.PM25
import com.allen.weatherapp.App
import com.allen.weatherapp.di.AppModule
import com.allen.weatherapp.di.TestRemoteModule
import com.allen.weatherapp.ui.pm25search.detail.Pm25Module
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectModule
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectNavigator
import com.allen.weatherapp.ui.weatherforecast.countyselect.WfCountySelectVM
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
class PmCountySelectVMTest : KoinTest {

    lateinit var mockWebServer : MockWebServer

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        stopKoin()

        startKoin {
            androidLogger()
            androidContext(ApplicationProvider.getApplicationContext<App>())
            modules(listOf(PmCountySelectModule, TestRemoteModule, AppModule))
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
    fun remoteGetPM25_Test() {
        println("start test")

        val gson = GsonBuilder().create()

        mockWebServer.url("/")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(
                    gson.toJson(arrayListOf( EPA_TestData.PM25_Test1)).toString()
                )
        )

        val navigator = spyk(object : PmCountySelectNavigator {
            override fun gotoPm25DetailFail() {
                print("gotoPm25Detail fail")
            }

            override fun gotoPm25Detail(arrayOfPM25: ArrayList<PM25>) {
                print("gotoPm25Detail succeed")
            }

        })

        val countySelectVM = get<PmCountySelectVM> { parametersOf(navigator) }

        countySelectVM.remoteGetPM25(EPA_API_CountyCode.高雄市)

        verify {navigator.gotoPm25Detail(any())}
    }

    @Test
    fun remoteGetPM25_FailTest() {
        println("start test")

        val gson = GsonBuilder().create()

        mockWebServer.url("/")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(400)
                .setBody(
                    ""
                )
        )

        val navigator = spyk(object : PmCountySelectNavigator {
            override fun gotoPm25DetailFail() {
                print("gotoPm25Detail fail")
            }

            override fun gotoPm25Detail(arrayOfPM25: ArrayList<PM25>) {
                print("gotoPm25Detail succeed")
            }

        })

        val countySelectVM = get<PmCountySelectVM> { parametersOf(navigator) }

        countySelectVM.remoteGetPM25(EPA_API_CountyCode.高雄市)

        verify {navigator.gotoPm25DetailFail()}
    }
}