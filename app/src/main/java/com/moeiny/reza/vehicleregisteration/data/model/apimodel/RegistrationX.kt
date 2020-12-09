package com.moeiny.reza.vehicleregisteration.data.model.apimodel

import com.google.gson.annotations.SerializedName

data class RegistrationX(
    val expired: Boolean?,
    @SerializedName("expiry_date")
    val expiryDate: String?
)