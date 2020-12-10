package com.moeiny.reza.vehicleregisteration.ui.detailsinfo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.vehicleregisteration.AndroidApplication
import com.moeiny.reza.vehicleregisteration.R
import com.moeiny.reza.vehicleregisteration.core.viewmodel.MyViewModelFactory
import com.moeiny.reza.vehicleregisteration.databinding.ActivityDetailBinding


class DetailsActivity : AppCompatActivity() {

    lateinit var viewModel: DetailsViewModel
    lateinit var mBinding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * assigning views and parrameters
         */
        setupView()
        /**
         *Receive the PlateNumber of vehicles that selected on recyclerview
         */

        var platenumber = intent.extras?.getString(PLATE_NUMBER)
        if (!platenumber.isNullOrEmpty()) {
            /**
             * fine the vehicles with the selected plateNumber in the list
             */

            viewModel.getSelectedItem(platenumber)
        }
        /**
         * SetUp all the livedata parameters to start their job(Observing data)
         */

        setupLiveData()
    }

    private fun setupView() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(application as AndroidApplication)).get(
                DetailsViewModel::class.java )
    }

    private fun setupLiveData() {
        viewModel.vehicleLiveData.observe(this, Observer { vehicleList ->
            mBinding.vehicle = vehicleList[0]
        })

        viewModel.loadingLiveData.observe(this, Observer {
            Toast.makeText(this, "Loading Data", Toast.LENGTH_SHORT).show()
        })

        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, "error on loading Data", Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        const val PLATE_NUMBER = "plateNumber"
    }

}







