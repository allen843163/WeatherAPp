package com.allen.weatherapp.ui.pm25search.countyselect

import com.allen.core.remote.epa.model.PM25

interface PmCountySelectNavigator {
    fun gotoPm25Detail(arrayOfPM25 : ArrayList<PM25>)
}