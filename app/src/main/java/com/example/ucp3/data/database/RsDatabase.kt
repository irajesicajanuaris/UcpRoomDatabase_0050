package com.example.ucp3.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp3.data.dao.DokterDao
import com.example.ucp3.data.entity.Dokter

@Database(entities = [Dokter::class], version = 1, exportSchema = false)
abstract class RsDatabase : RoomDatabase() {
    //mendefinisikan fungsi untuk mengakses data Dokter
    abstract fun dokterDao(): DokterDao

    companion object{
        @Volatile // memastikan bahwa nilai variabel instance selalu sama di semua
        private var Instance: RsDatabase?= null

        fun getDatabase(context: Context): RsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    RsDatabase::class.java, //class database
                    "RsDatabase" // Nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}



