package com.ims.coviddata.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Statewise {

    @SerializedName("active")
    var active: String = ""

    @SerializedName("deaths")
    var deaths: String = ""

    @SerializedName("recovered")
    var recovered: String = ""

    @SerializedName("state")
    var state: String = ""

    @PrimaryKey(autoGenerate = false)
    @SerializedName("statecode")
    var statecode: String = ""


    @SerializedName("confirmed")
    @Ignore
    var confirmed: String? = null

    @SerializedName("deltaconfirmed")
    @Ignore
    var deltaconfirmed: String? = null

    @SerializedName("deltadeaths")
    @Ignore
    var deltadeaths: String? = null

    @SerializedName("deltarecovered")
    @Ignore
    var deltarecovered: String? = null

    @SerializedName("lastupdatedtime")
    @Ignore
    var lastupdatedtime: String? = null

    @SerializedName("migratedother")
    @Ignore
    var migratedother: String? = null

    @SerializedName("statenotes")
    @Ignore
    var statenotes: String? = null

    @Ignore
    var isInserted: Boolean = false
}
