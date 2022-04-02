package com.ims.coviddata.models

import com.google.gson.annotations.SerializedName

data class CovidResponseData(

    @SerializedName("cases_time_series") var casesTimeSeries: ArrayList<Cases> = arrayListOf(),
    @SerializedName("statewise") var statewise: ArrayList<Statewise> = arrayListOf(),
    @SerializedName("tested") var tested: ArrayList<Tested> = arrayListOf()

)