package com.ims.coviddata.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
class Cases {
    @PrimaryKey(autoGenerate = false)
    var date: String = ""
    var totalconfirmed: String = ""
    var totaldeceased: String = ""
    var totalrecovered: String = ""
    var cases_time_series: String = ""
    @Ignore
    var dailyconfirmed: String = ""
    @Ignore
    var dailydeceased: String = ""
    @Ignore
    var dailyrecovered: String = ""
    @Ignore
    var isInserted: Boolean = false


}
