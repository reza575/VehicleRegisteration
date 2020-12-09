package com.moeiny.reza.vehicleregisteration.data.database.dao

import androidx.room.*
import com.moeiny.reza.vehicleregisteration.data.database.entitiy.VehicleEntity

@Dao
interface VehicleDao {

    @Query("SELECT * FROM Vehicles ORDER BY plateNumber ")
    fun getAll(): List<VehicleEntity>

    @Query("SELECT * FROM Vehicles WHERE plateNumber = :plateNumber ")
    fun findByPlateid(plateNumber: String): VehicleEntity

    @Query("DELETE FROM Vehicles")
    fun deleteAll()

    @Insert
    fun insert(vehicle: VehicleEntity)

    @Update
    fun update(vehicle: VehicleEntity)

    @Delete
    fun delete(vehicle: VehicleEntity)
}




