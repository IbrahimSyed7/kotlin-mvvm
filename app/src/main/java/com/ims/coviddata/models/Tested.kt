package com.ims.coviddata.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Tested {

    @SerializedName("dailyrtpcrsamplescollectedicmrapplication")
    var dailyrtpcrsamplescollectedicmrapplication: String = ""

    @SerializedName("totaldosesadministered")
    var totaldosesadministered: String = ""

    @SerializedName("samplereportedtoday")
    var samplereportedtoday: String = ""

    @Ignore
    @SerializedName("firstdoseadministered")
    var firstdoseadministered: String? = null

    @Ignore
    @SerializedName("frontlineworkersvaccinated1stdose")
    var frontlineworkersvaccinated1stdose: String? = null

    @Ignore
    @SerializedName("frontlineworkersvaccinated2nddose")
    var frontlineworkersvaccinated2nddose: String? = null

    @Ignore
    @SerializedName("healthcareworkersvaccinated1stdose")
    var healthcareworkersvaccinated1stdose: String? = null

    @Ignore
    @SerializedName("healthcareworkersvaccinated2nddose")
    var healthcareworkersvaccinated2nddose: String? = null

    @Ignore
    @SerializedName("over45years1stdose")
    var over45years1stdose: String? = null

    @Ignore
    @SerializedName("over45years2nddose")
    var over45years2nddose: String? = null

    @Ignore
    @SerializedName("over60years1stdose")
    var over60years1stdose: String? = null

    @Ignore
    @SerializedName("over60years2nddose")
    var over60years2nddose: String? = null

    @Ignore
    @SerializedName("positivecasesfromsamplesreported")
    var positivecasesfromsamplesreported: String? = null

    @Ignore
    @SerializedName("registration18-45years")
    var registration18_45years: String? = null

    @Ignore
    @SerializedName("registrationabove45years")
    var registrationabove45years: String? = null

    @Ignore
    @SerializedName("registrationflwhcw")
    var registrationflwhcw: String? = null

    @Ignore
    @SerializedName("registrationonline")
    var registrationonline: String? = null

    @Ignore
    @SerializedName("registrationonspot")
    var registrationonspot: String? = null

    @Ignore
    @SerializedName("seconddoseadministered")
    var seconddoseadministered: String? = null

    @SerializedName("source")
    var source: String? = null

    @Ignore
    @SerializedName("source2")
    var source2: String? = null

    @Ignore
    @SerializedName("source3")
    var source3: String? = null

    @Ignore
    @SerializedName("source4")
    var source4: String? = null

    @Ignore
    @SerializedName("testedasof")
    var testedasof: String? = null

    @Ignore
    @SerializedName("testsconductedbyprivatelabs")
    var testsconductedbyprivatelabs: String? = null

    @Ignore
    @SerializedName("to60yearswithco-morbidities1stdose")
    var to60yearswithco_morbidities1stdose: String? = null

    @Ignore
    @SerializedName("to60yearswithco-morbidities2nddose")
    var to60yearswithco_morbidities2nddose: String? = null

    @Ignore
    @SerializedName("totaldosesavailablewithstates")
    var totaldosesavailablewithstates: String? = null

    @Ignore
    @SerializedName("totaldosesavailablewithstatesprivatehospitals")
    var totaldosesavailablewithstatesprivatehospitals: String? = null

    @Ignore
    @SerializedName("totaldosesinpipeline")
    var totaldosesinpipeline: String? = null

    @Ignore
    @SerializedName("totaldosesprovidedtostatesuts")
    var totaldosesprovidedtostatesuts: String? = null

    @Ignore
    @SerializedName("totalindividualsregistered")
    var totalindividualsregistered: String? = null

    @Ignore
    @SerializedName("totalindividualstested")
    var totalindividualstested: String? = null

    @Ignore
    @SerializedName("totalpositivecases")
    var totalpositivecases: String? = null

    @Ignore
    @SerializedName("totalrtpcrsamplescollectedicmrapplication")
    var totalrtpcrsamplescollectedicmrapplication: String? = null

    @Ignore
    @SerializedName("totalsamplestested")
    var totalsamplestested: String? = null

    @Ignore
    @SerializedName("totalsessionsconducted")
    var totalsessionsconducted: String? = null

    @Ignore
    @SerializedName("totalvaccineconsumptionincludingwastage")
    var totalvaccineconsumptionincludingwastage: String? = null

    @PrimaryKey(autoGenerate = false)
    @SerializedName("updatetimestamp")
    var updatetimestamp: String = ""

    @Ignore
    @SerializedName("years1stdose")
    var years1stdose: String? = null

    @Ignore
    @SerializedName("years2nddose")
    var years2nddose: String? = null

    @Ignore
    var isInserted: Boolean = false
}
