package com.ims.coviddata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Statewise(

    @SerializedName("active") var active: String = "",
    @SerializedName("deaths") var deaths: String = "",
    @SerializedName("recovered") var recovered: String = "",
    @SerializedName("state") var state: String = "",
    @PrimaryKey(autoGenerate = false)
    @SerializedName("statecode") var statecode: String = ""
    //    @SerializedName("confirmed") var confirmed: String? = null,
//    @SerializedName("deltaconfirmed") var deltaconfirmed: String? = null,
//    @SerializedName("deltadeaths") var deltadeaths: String? = null,
//    @SerializedName("deltarecovered") var deltarecovered: String? = null,
//    @SerializedName("lastupdatedtime") var lastupdatedtime: String? = null,
//    @SerializedName("migratedother") var migratedother: String? = null,

//    @SerializedName("statenotes") var statenotes: String? = null
)
