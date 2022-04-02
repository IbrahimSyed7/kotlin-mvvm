package com.ims.coviddata.repo

import com.ims.coviddata.database.MainRoomDatabase
import com.ims.coviddata.models.Cases
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested
import com.ims.coviddata.network.NetworkService

class MainRepository(val networkService: NetworkService, val database: MainRoomDatabase) {
    fun getAllData() = networkService.getAllData()


    suspend fun insertCase(cases: Cases) = database.getMainDao().insertCase(cases)
    suspend fun deleteCase(cases: Cases) = database.getMainDao().deleteCase(cases)

    suspend fun insertState(statewise: Statewise) = database.getMainDao().insertState(statewise)
    suspend fun deleteState(statewise: Statewise) = database.getMainDao().deleteState(statewise)

    suspend fun insertTest(tested: Tested) = database.getMainDao().insertTest(tested)
    suspend fun deleteTest(tested: Tested) = database.getMainDao().deleteTest(tested)

    fun allCases() = database.getMainDao().getCases()
    fun allStates() = database.getMainDao().getStates()
    fun allTested() = database.getMainDao().getTest()
}