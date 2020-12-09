package com.moeiny.reza.vehicleregisteration.data.vehiclerepository

import android.app.Application
import android.os.AsyncTask
import com.moeiny.reza.vehicleregisteration.core.result.Result
import com.moeiny.reza.vehicleregisteration.data.database.AppDatabase
import com.moeiny.reza.vehicleregisteration.data.database.dao.VehicleDao
import com.moeiny.reza.vehicleregisteration.data.database.entitiy.VehicleEntity
import com.moeiny.reza.vehicleregisteration.data.model.apimodel.VehiclesInfoModel
import com.moeiny.reza.vehicleregisteration.data.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class NewsRepositoryDefault trying to  receive data from Api nd this class has 3 state:
 * 1- Loading : When data started to load from API until loading completed.
 * 2- Response: Data has loaded completely without any error. this data deliver to App in this state.
 * 3- error: if any error happen during loading data this error will be handel by any predefined decision.
 */

class VehiclesRepositoryDefault(private val apiService: ApiService) : VehiclesRepository{
    override fun fetchVehiclesInfo(onResult: (result: Result<VehiclesInfoModel>) -> Unit) {

        onResult(Result.Loading)

        apiService.getVehiclesInfo().enqueue(object :Callback<VehiclesInfoModel>{
            override fun onFailure(call: Call<VehiclesInfoModel>, t: Throwable) {
                onResult(Result.Error(t))
            }

            override fun onResponse(call: Call<VehiclesInfoModel>, response: Response<VehiclesInfoModel>) {
                response.body()?.let {
                    onResult(Result.Success(it))
                }
            }
        })
    }
}

interface VehiclesRepository {
    fun fetchVehiclesInfo(onResult: (result: Result<VehiclesInfoModel>) -> Unit)
}




