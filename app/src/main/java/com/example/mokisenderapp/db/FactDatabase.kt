package com.example.mokisenderapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mokisenderapp.Defs
import com.example.mokisenderapp.db.entities.Fact

@Database(entities = [Fact::class], version = 1, exportSchema = false)
abstract class FactDatabase : RoomDatabase() {

    abstract val factDao: FactDao

    companion object {
        @Volatile
        private var INSTANCE: FactDatabase? = null

        fun getInstance(context: Context): FactDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FactDatabase::class.java,
                        Defs.FACT_DB_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}