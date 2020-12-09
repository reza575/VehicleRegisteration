package com.moeiny.reza.vehicleregisteration.data.retrofit


import com.moeiny.reza.vehicleregisteration.data.model.apimodel.VehiclesInfoModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("3fqZZ3Y")
    // Communicates responses from a server are executed on the background thread which performed the request
    fun getVehiclesInfo(): Call<VehiclesInfoModel>
}