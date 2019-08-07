package com.allen.weatherapp.remote.model.cwb

import android.annotation.SuppressLint
import android.os.Parcelable
import com.allen.weatherapp.Variable
import kotlinx.android.parcel.Parcelize


@Parcelize
class WeatherForecast : Parcelable {

    companion object {
        const val route : String = "api/v1/rest/datastore"
    }

    data class Request (
        val Authorization : String = Variable.CWB_Token,

        val limit : Number? = null,

        val offset : Number? = null,

        val format : String = "JSON",

        val locationName : List<String>? = null,

        var elementName : List<String>? = null,

        val sort : String? = null,

        val startTime : List<String>? = null,

        val timeTo : List<String>? = null)

    data class Response(
        val records: Records,
        val result: Result,
        val success: String){


        @Parcelize
        data class Records(
            val locations: List<Location>
        ) : Parcelable

        @Parcelize
        data class Location(
            val dataid: String,
            val datasetDescription: String,
            val location: List<LocationX>,
            val locationsName: String
        ) : Parcelable

        @Parcelize
        data class LocationX(
            val geocode: String,
            val lat: String,
            val locationName: String,
            val lon: String,
            val weatherElement: List<WeatherElement>
        ) : Parcelable

        @Parcelize
        data class WeatherElement(
            val description: String,
            val elementName: String,
            val time: List<Time>
        ) : Parcelable

        @Parcelize
        data class Time(
            val elementValue: List<ElementValue>,
            val endTime: String,
            val startTime: String
        ) : Parcelable

        @Parcelize
        data class ElementValue(
            val measures: String,
            val value: String
        ) : Parcelable

        data class Result(
            val fields: List<Field>,
            val resource_id: String
        )

        data class Field(
            val id: String,
            val type: String
        )
    }
}