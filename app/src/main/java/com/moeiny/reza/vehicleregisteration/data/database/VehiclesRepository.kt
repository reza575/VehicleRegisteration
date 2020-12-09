package com.moeiny.reza.vehicleregisteration.data.database

import android.app.Application
import android.os.AsyncTask
import com.moeiny.reza.vehicleregisteration.data.database.dao.VehicleDao
import com.moeiny.reza.vehicleregisteration.data.database.entitiy.VehicleEntity


class VehiclesRepository(application: Application){

    private  var vehicleDao: VehicleDao

    private  var allVehiclesData:List<VehicleEntity>

    init {
        val db: AppDatabase = AppDatabase.getInstance(
            application.applicationContext )!!

        vehicleDao = db.VehicleDao()

        allVehiclesData = vehicleDao.getAll()

    }

    //////////////// implementing Function to Access Vehicles Details on Database//////////

    fun insertVehicle(vehicleEntity: VehicleEntity){
        VehicleInsert(vehicleDao).execute(vehicleEntity)
    }

    fun updateVehicle(vehicleEntity: VehicleEntity){
        VehicleUpdate(vehicleDao).execute(vehicleEntity)
    }

    fun deleteVehicle(vehicleEntity: VehicleEntity){
        VehicleDelete(vehicleDao).execute(vehicleEntity)
    }

    fun deleteAllVehicles(){
        vehicleDao.deleteAll()
    }

    fun getAllVehicles():List<VehicleEntity>{
        return allVehiclesData
    }

    fun getVehicleById(plateNumber:String): VehicleEntity {
        return vehicleDao.findByPlateid(plateNumber)
    }

    private class VehicleInsert(vehicleDao: VehicleDao): AsyncTask<VehicleEntity, Void, Void>(){

        private var userDao: VehicleDao
        init{
            this.userDao=vehicleDao
        }

        override fun doInBackground(vararg p0: VehicleEntity): Void? {
            userDao.insert(p0[0])
            return null
        }

    }

    private class VehicleUpdate(vehicleDao: VehicleDao): AsyncTask<VehicleEntity, Void, Void>(){

        private var vehicleDao: VehicleDao
        init{
            this.vehicleDao=vehicleDao
        }

        override fun doInBackground(vararg p0: VehicleEntity): Void? {
            vehicleDao.update(p0[0])
            return null
        }
    }

    private class VehicleDelete(vehicleDao: VehicleDao): AsyncTask<VehicleEntity, Void, Void>(){

        private var vehicleDao: VehicleDao
        init{
            this.vehicleDao=vehicleDao
        }

        override fun doInBackground(vararg p0: VehicleEntity): Void? {
            vehicleDao.delete(p0[0])
            return null
        }
    }
}




