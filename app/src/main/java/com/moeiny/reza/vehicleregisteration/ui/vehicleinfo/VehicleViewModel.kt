package com.moeiny.reza.vehicleregisteration.ui.vehicleinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moeiny.reza.vehicleregisteration.core.result.Result
import com.moeiny.reza.vehicleregisteration.data.model.uimodel.ShowVehicleModel
import com.moeiny.reza.vehicleregisteration.data.vehiclerepository.VehiclesRepository


class VehicleViewModel(private val vehicleRepository: VehiclesRepository) : ViewModel() {

    val vehicleLiveData = MutableLiveData<List<ShowVehicleModel>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    val showVehiclesLiveData = MutableLiveData<String>()

    val errorLiveData = MutableLiveData<Boolean>()

    /**
     * getAllData function:fetching data from repository by considering 3 state of onSuccess,onError,onLoading
     */

    fun getAllData() {
        vehicleRepository.fetchVehiclesInfo { result ->
            if (result is Result.Success) {

                //Map News Data from API model to UI Model
                val vehicleList = result.data.registrations.map { vehicle ->
                    ShowVehicleModel(
                        plateNumber = vehicle.plateNumber.orEmpty(),
                        make = vehicle.vehicle?.make.orEmpty(),
                        model = vehicle.vehicle?.model.orEmpty(),
                        expiryStatus = if (vehicle.registration?.expired == true) "Expired" else "Active"
                    )
                }
                vehicleLiveData.postValue(vehicleList)
            } else if (result is Result.Loading) {
                loadingLiveData.postValue(true)
            } else if (result is Result.Error) {
                errorLiveData.postValue(true)
            }
        }
    }

    fun onCardClicked(plateNumber: String) {
        showVehiclesLiveData.postValue(plateNumber)
    }

}