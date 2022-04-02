package com.ims.coviddata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ims.coviddata.models.Cases
import com.ims.coviddata.models.Statewise
import com.ims.coviddata.models.Tested


@Database(entities = [Cases::class, Statewise::class, Tested::class], version = 3)
abstract class MainRoomDatabase : RoomDatabase() {

    abstract fun getMainDao(): MainDao

    companion object {
        @Volatile
        private var instance: MainRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context, MainRoomDatabase::class.java, "MainRoom.db")
                .fallbackToDestructiveMigration().build()
    }
}