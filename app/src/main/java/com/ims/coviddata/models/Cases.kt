package com.ims.coviddata.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cases(
    @PrimaryKey(autoGenerate = false)
    val date: String,
    val totalconfirmed: String = "",
    val totaldeceased: String = "",
    val totalrecovered: String = "",
    var cases_time_series: String = ""
)
