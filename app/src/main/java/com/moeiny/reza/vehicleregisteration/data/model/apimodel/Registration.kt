package com.moeiny.reza.vehicleregisteration.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class Registration(
    val insurer: Insurer?,
    @SerializedName("plate_number")
    val plateNumber: String?,
    val registration: RegistrationX?,
    val vehicle: Vehicle?
)