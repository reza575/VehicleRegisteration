package com.moeiny.reza.vehicleregisteration.ui.vehicleinfo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moeiny.reza.vehicleregisteration.AndroidApplication
import com.moeiny.reza.vehicleregisteration.R
import com.moeiny.reza.vehicleregisteration.core.viewmodel.MyViewModelFactory
import com.moeiny.reza.vehicleregisteration.databinding.ActivityMainBinding
import com.moeiny.reza.vehicleregisteration.ui.detailsinfo.DetailsActivity


class VehicleActivity : AppCompatActivity() {

    lateinit var viewModel: VehicleViewModel
    lateinit var mBinding: ActivityMainBinding

    private val adapter:VehicleAdapter by lazy {
        VehicleAdapter(viewModel::onCardClicked)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * function setUpVies: Assign parameters and values
         */
        setUpViews()
        /**
         * SetUp all the livedata parameters to start their job(Observing data)
         */
        setupLiveData()
    }

    private fun setUpViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, MyViewModelFactory( application as AndroidApplication)).get(
            VehicleViewModel::class.java )
        viewModel.getAllData()
        mBinding.recyclerView.adapter = adapter
    }

    private fun setupLiveData(){
        viewModel.vehicleLiveData.observe(this, Observer { vehicleList->
            mBinding.loadingPanel.visibility = if(vehicleList.isNotEmpty()) View.GONE else View.VISIBLE
            adapter.showVehicleList = vehicleList
        })

        viewModel.loadingLiveData.observe(this, Observer {
            mBinding.loadingPanel.visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this,"error on loading Data",Toast.LENGTH_SHORT).show()
        })

        viewModel.showVehiclesLiveData.observe(this, Observer { plateNumber ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.PLATE_NUMBER, plateNumber)
            this.startActivity(intent)
        })
    }
}
