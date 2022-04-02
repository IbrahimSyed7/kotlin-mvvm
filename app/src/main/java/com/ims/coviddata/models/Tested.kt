package com.ims.coviddata.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tested(

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    @SerializedName("dailyrtpcrsamplescollectedicmrapplication") var dailyrtpcrsamplescollectedicmrapplication: String = "",
    @SerializedName("totaldosesadministered") var totaldosesadministered: String = "",
    @SerializedName("samplereportedtoday") var samplereportedtoday: String = "",

//    @SerializedName("firstdoseadministered") var firstdoseadministered: String? = null,
//    @SerializedName("frontlineworkersvaccinated1stdose") var frontlineworkersvaccinated1stdose: String? = null,
//    @SerializedName("frontlineworkersvaccinated2nddose") var frontlineworkersvaccinated2nddose: String? = null,
//    @SerializedName("healthcareworkersvaccinated1stdose") var healthcareworkersvaccinated1stdose: String? = null,
//    @SerializedName("healthcareworkersvaccinated2nddose") var healthcareworkersvaccinated2nddose: String? = null,
//    @SerializedName("over45years1stdose") var over45years1stdose: String? = null,
//    @SerializedName("over45years2nddose") var over45years2nddose: String? = null,
//    @SerializedName("over60years1stdose") var over60years1stdose: String? = null,
//    @SerializedName("over60years2nddose") var over60years2nddose: String? = null,
//    @SerializedName("positivecasesfromsamplesreported") var positivecasesfromsamplesreported: String? = null,
//    @SerializedName("registration18-45years") var registration18_45years: String? = null,
//    @SerializedName("registrationabove45years") var registrationabove45years: String? = null,
//    @SerializedName("registrationflwhcw") var registrationflwhcw: String? = null,
//    @SerializedName("registrationonline") var registrationonline: String? = null,
//    @SerializedName("registrationonspot") var registrationonspot: String? = null,
//    @SerializedName("seconddoseadministered") var seconddoseadministered: String? = null,
//    @SerializedName("source") var source: String? = null,
//    @SerializedName("source2") var source2: String? = null,
//    @SerializedName("source3") var source3: String? = null,
//    @SerializedName("source4") var source4: String? = null,
//    @SerializedName("testedasof") var testedasof: String? = null,
//    @SerializedName("testsconductedbyprivatelabs") var testsconductedbyprivatelabs: String? = null,
//    @SerializedName("to60yearswithco-morbidities1stdose") var to60yearswithco_morbidities1stdose: String? = null,
//    @SerializedName("to60yearswithco-morbidities2nddose") var to60yearswithco_morbidities2nddose: String? = null,
//    @SerializedName("totaldosesavailablewithstates") var totaldosesavailablewithstates: String? = null,
//    @SerializedName("totaldosesavailablewithstatesprivatehospitals") var totaldosesavailablewithstatesprivatehospitals: String? = null,
//    @SerializedName("totaldosesinpipeline") var totaldosesinpipeline: String? = null,
//    @SerializedName("totaldosesprovidedtostatesuts") var totaldosesprovidedtostatesuts: String? = null,
//    @SerializedName("totalindividualsregistered") var totalindividualsregistered: String? = null,
//    @SerializedName("totalindividualstested") var totalindividualstested: String? = null,
//    @SerializedName("totalpositivecases") var totalpositivecases: String? = null,
//    @SerializedName("totalrtpcrsamplescollectedicmrapplication") var totalrtpcrsamplescollectedicmrapplication: String? = null,
//    @SerializedName("totalsamplestested") var totalsamplestested: String? = null,
//    @SerializedName("totalsessionsconducted") var totalsessionsconducted: String? = null,
//    @SerializedName("totalvaccineconsumptionincludingwastage") var totalvaccineconsumptionincludingwastage: String? = null,
//    @SerializedName("updatetimestamp") var updatetimestamp: String? = null,
//    @SerializedName("years1stdose") var years1stdose: String? = null,
//    @SerializedName("years2nddose") var years2nddose: String? = null

)