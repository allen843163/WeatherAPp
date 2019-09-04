package com.allen.weatherapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.allen.weatherapp.di.AppModule
import com.allen.weatherapp.di.RemoteModule
import com.allen.weatherapp.di.ViewModelModule
import com.allen.weatherapp.di.WeatherForecastModule
import com.allen.weatherapp.remote.CWB_API_CountyCode
import com.allen.weatherapp.ui.countyselect.CountySelectNavigator
import com.allen.weatherapp.ui.countyselect.CountySelectVM
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.ArgumentMatchers

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest :KoinTest {

}
