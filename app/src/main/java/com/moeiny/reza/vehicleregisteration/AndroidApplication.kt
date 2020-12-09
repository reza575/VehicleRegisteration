package com.moeiny.reza.vehicleregisteration

import android.app.Application
import com.moeiny.reza.vehicleregisteration.data.vehiclerepository.VehiclesRepository
import com.moeiny.reza.vehicleregisteration.data.vehiclerepository.VehiclesRepositoryDefault
import com.moeiny.reza.vehicleregisteration.data.retrofit.ApiService


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AndroidApplication: Application() {

    val vehicleRepository : VehiclesRepository by lazy {
        VehiclesRepositoryDefault(apiService)
    }

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://bit.ly/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    override fun onCreate() {
        super.onCreate()

    }
}