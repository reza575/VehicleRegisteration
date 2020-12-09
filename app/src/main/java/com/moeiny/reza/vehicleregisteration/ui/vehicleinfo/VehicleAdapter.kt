package com.moeiny.reza.vehicleregisteration.ui.vehicleinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moeiny.reza.vehicleregisteration.data.model.uimodel.ShowVehicleModel
import com.moeiny.reza.vehicleregisteration.databinding.VehicleBinding

class VehicleAdapter(private val onCardClicked: (plateNumber: String) -> Unit) :
    RecyclerView.Adapter<VehicleAdapter.AssetViewHolder>() {

    var showVehicleList: List<ShowVehicleModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val vehicleBinding = VehicleBinding.inflate(layoutInflater, parent, false)

        return AssetViewHolder(vehicleBinding)
    }

    override fun getItemCount(): Int {
        return showVehicleList.count()
    }

    override fun onBindViewHolder(holderAsset: AssetViewHolder, position: Int) {
        var vehicle = showVehicleList.get(position)
        holderAsset.vehicleBinding.cardVehiclerowParent.setOnClickListener {
            onCardClicked(vehicle.plateNumber)
        }

        holderAsset.bind(vehicle)
    }

    inner class AssetViewHolder(val vehicleBinding: VehicleBinding) :
        RecyclerView.ViewHolder(vehicleBinding.root) {
        fun bind(modelViewShow: ShowVehicleModel) {
            this.vehicleBinding.vehicle = modelViewShow
        }
    }

}