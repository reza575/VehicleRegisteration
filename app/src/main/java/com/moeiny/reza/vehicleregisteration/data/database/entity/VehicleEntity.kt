package com.moeiny.reza.vehicleregisteration.data.database.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Vehicles")
class VehicleEntity (@PrimaryKey   var plateNumber:String,
                     @ColumnInfo var colour: String,
                     @ColumnInfo var grossMass: String,
                     @ColumnInfo var make: String,
                     @ColumnInfo var model: String,
                     @ColumnInfo var tareWeight: String,
                     @ColumnInfo var type: String,
                     @ColumnInfo var vin: String,
                     @ColumnInfo var expiryStatus: String,
                     @ColumnInfo var expiryDate: String,
                     @ColumnInfo var insurerCode: String,
                     @ColumnInfo var insurerName: String
)

