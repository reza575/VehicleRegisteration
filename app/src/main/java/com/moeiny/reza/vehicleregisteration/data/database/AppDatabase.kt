package com.moeiny.reza.vehicleregisteration.data.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moeiny.reza.vehicleregisteration.data.database.dao.VehicleDao
import com.moeiny.reza.vehicleregisteration.data.database.entitiy.VehicleEntity


@Database(entities = [(VehicleEntity::class)], version = 1, exportSchema = false)

public abstract class AppDatabase : RoomDatabase() {

    abstract fun VehicleDao(): VehicleDao


    companion object {

        private var instance: AppDatabase? = null
        public var DB_NAME = "Vehicles"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(instance)
                    .execute()
        }
    }

}

    class PopulateDbAsyncTask(db: AppDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val vehicleDao = db?.VehicleDao()

    override fun doInBackground(vararg p0: Unit?) {
      }
    }

