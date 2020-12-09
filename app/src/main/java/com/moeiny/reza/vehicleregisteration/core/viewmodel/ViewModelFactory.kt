package com.moeiny.reza.vehicleregisteration.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.vehicleregisteration.AndroidApplication
import com.moeiny.reza.vehicleregisteration.ui.detailsinfo.DetailsViewModel
import com.moeiny.reza.vehicleregisteration.ui.vehicleinfo.VehicleViewModel
import java.lang.IllegalArgumentException

/**
 * A  class that create a model class with base of NewsViewModel.
 */
class MyViewModelFactory(private val application: AndroidApplication) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return if (modelClass == VehicleViewModel::class.java )
            VehicleViewModel(application.vehicleRepository) as T
         else if (modelClass == DetailsViewModel::class.java)
            DetailsViewModel(application.vehicleRepository) as T
        else
            throw IllegalArgumentException()
    }
}
