package com.allen.weatherapp.ui.pm25search.countyselect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.allen.core.remote.epa.EPA_API_CountyCode
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class PmCountySelectVM(application : Application,
                            var pmCountySelectNavigator : PmCountySelectNavigator,
                            var pmCountySelectImpl: PmCountySelectImpl
) : AndroidViewModel(application) {

    fun remoteGetPM25(countyCode: EPA_API_CountyCode) {
        pmCountySelectImpl.getPM25(countyCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    pmCountySelectNavigator.gotoPm25Detail(it)
                },
                {
                    pmCountySelectNavigator.gotoPm25DetailFail()
                    it.printStackTrace()
                }
            )
    }
}