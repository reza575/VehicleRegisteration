package com.moeiny.reza.vehicleregisteration.ui.detailsinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moeiny.reza.vehicleregisteration.core.result.Result
import com.moeiny.reza.vehicleregisteration.data.model.uimodel.ShowDetailsModel
import com.moeiny.reza.vehicleregisteration.data.vehiclerepository.VehiclesRepository
import java.text.SimpleDateFormat
import java.util.*


class DetailsViewModel(private val vehicleRepository: VehiclesRepository) : ViewModel() {

    val vehicleLiveData = MutableLiveData<List<ShowDetailsModel>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<Boolean>()

    /**
     * getAllNews function:fetching news data from repository by considering 3 state of onSuccess,onError,onLoading
     */

    fun getSelectedItem(plateNumber: String) {

        if (plateNumber.isNotEmpty()) {

            vehicleRepository.fetchVehiclesInfo { result ->
                if (result is Result.Success) {
                    val selectedList =
                        result.data.registrations.filter { it.plateNumber == plateNumber }

                    //Map News Data from API model to UI Model
                    val registeration = selectedList.map { vehicle ->
                        ShowDetailsModel(
                            plateNumber = vehicle.plateNumber.orEmpty(),
                            type = vehicle.vehicle?.type.orEmpty(),
                            make = vehicle.vehicle?.make.orEmpty(),
                            model = vehicle.vehicle?.model.orEmpty(),
                            colour = vehicle.vehicle?.colour.orEmpty(),
                            vin = if(vehicle.vehicle?.vin.isNullOrEmpty()) ""
                                    else (vehicle.vehicle?.vin!!.substring(vehicle.vehicle?.vin!!.length-4)),
                            tareWeight = vehicle.vehicle?.tareWeight.toString(),
                            grossMass = vehicle.vehicle?.grossMass.toString(),
                            expiryStatus = if (vehicle.registration?.expired == true) "Expired" else "Active",
                            expiryDate = convertDate(vehicle.registration?.expiryDate.orEmpty()),
                            validmonth = validateMonth(vehicle.registration?.expiryDate.orEmpty()),
                            insurerCode = vehicle.insurer?.code.toString(),
                            insurerName = vehicle.insurer?.name.orEmpty()
                        )
                    }
                    vehicleLiveData.postValue(registeration)
                } else if (result is Result.Loading) {
                    loadingLiveData.postValue(true)
                } else if (result is Result.Error) {
                    errorLiveData.postValue(true)
                }
            }
        }
    }

    /**
     * convertDate function: convert the format of date
     */
    fun convertDate(date: String): String {
        if (!date.isNullOrEmpty()) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val parsedDate: Date = inputFormat.parse(date)
            val sdf = SimpleDateFormat("dd-MMM-yyyy")
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+10"))
            return sdf.format(parsedDate).toLowerCase()
        } else {
            return ""
        }
    }
    /**
     * validateMonth function: calculate the period of validity for vehicles by consider of expiry date
     */
    fun validateMonth(myDate: String): String {
        if (!myDate.isNullOrEmpty()) {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date = sdf.parse(myDate)
            val millis = (date.time / 1000) - (System.currentTimeMillis() / 1000)
            return if (millis > 0) (millis / (30 * 24 * 60 * 60) + 1).toString() + " Month(s)" else "0 Month"
        } else {
            return ""
        }
    }
}