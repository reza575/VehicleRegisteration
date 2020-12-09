package com.moeiny.reza.vehicleregisteration.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    fun getClient():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://bit.ly/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}