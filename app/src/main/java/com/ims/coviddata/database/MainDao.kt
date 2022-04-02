package com.ims.coviddata.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ims.coviddata.models.Cases
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested

@Dao
interface MainDao {

    //combined all tables queries in one dao because of it is 3 tables only

    @Insert(onConflict = REPLACE)
    suspend fun insertCase(cases: Cases)

    @Insert(onConflict = REPLACE)
    suspend fun insertState(statewise: Statewise)

    @Insert(onConflict = REPLACE)
    suspend fun insertTest(tested: Tested)

    @Delete()
    suspend fun deleteCase(cases: Cases)

    @Delete()
    suspend fun deleteState(statewise: Statewise)

    @Delete()
    suspend fun deleteTest(tested: Tested)

    @Query("Select * from cases")
    fun getCases(): LiveData<List<Cases>>

    @Query("Select * from statewise")
    fun getStates(): LiveData<List<Statewise>>

    @Query("Select * from tested")
    fun getTest(): LiveData<List<Tested>>

}