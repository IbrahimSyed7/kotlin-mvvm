package com.ims.coviddata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ims.coviddata.models.Cases
import com.ims.coviddata.models.CovidResponseData
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested
import com.ims.coviddata.repo.MainRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(val repository: MainRepository) : ViewModel() {

    //    val covidData = MutableLiveData<CovidResponseData>()
    val casesData = MutableLiveData<List<Cases>>()
    val stateWiseData = MutableLiveData<List<Statewise>>()
    val testedData = MutableLiveData<List<Tested>>()
    val errorMessage = MutableLiveData<String>()

    //remoteData
    fun getAllData() {
        val response = repository.getAllData()
        response.enqueue(object : Callback<CovidResponseData> {
            override fun onResponse(
                call: Call<CovidResponseData>,
                response: Response<CovidResponseData>
            ) {
                casesData.postValue(response.body()!!.casesTimeSeries)
                stateWiseData.postValue(response.body()!!.statewise)
                testedData.postValue(response.body()!!.tested)
            }

            override fun onFailure(call: Call<CovidResponseData>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }


    fun insertCase(cases: Cases) = GlobalScope.launch {
        repository.insertCase(cases)
    }

    fun insertState(statewise: Statewise) = GlobalScope.launch {
        repository.insertState(statewise)
    }

    fun insertTest(tested: Tested) = GlobalScope.launch {
        repository.insertTest(tested)
    }

    fun deleteCases(cases: Cases) = GlobalScope.launch {
        repository.deleteCase(cases)
    }

    fun deleteState(statewise: Statewise) = GlobalScope.launch {
        repository.deleteState(statewise)
    }

    fun deleteTest(tested: Tested) = GlobalScope.launch {
        repository.deleteTest(tested)
    }


    fun allCases() = repository.allCases()
    fun allStates() = repository.allStates()
    fun allTests() = repository.allTested()


}