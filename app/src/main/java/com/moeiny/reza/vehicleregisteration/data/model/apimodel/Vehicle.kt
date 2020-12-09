package com.moeiny.reza.vehicleregisteration.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class Vehicle(
    val colour: String?,
    @SerializedName("gross_mass")
    val grossMass: Int?,
    val make: String?,
    val model: String?,
    @SerializedName("tare_weight")
    val tareWeight: Int?,
    val type: String?,
    val vin: String?
)