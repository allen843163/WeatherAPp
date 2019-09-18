package com.allen.weatherapp.ui.pm25search.countyselect

import com.allen.core.remote.epa.EPA_API_CountyCode
import com.allen.core.remote.epa.EPA_ApiService
import com.allen.core.remote.epa.model.PM25

class PmCountySelectImpl (val EPAApiService: EPA_ApiService) {

    fun getPM25 (countyCode: EPA_API_CountyCode) = {
        EPAApiService.getPM25()
            .map {
                var filterList : ArrayList<PM25> = arrayListOf()

                it.forEach {pM25 ->
                    if(pM25.county.equals(countyCode.name)) {
                        filterList.add(pM25)
                    }
                }

                filterList
            }
    }.invoke()

}