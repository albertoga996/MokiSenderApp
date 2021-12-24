package com.example.mokisenderapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.example.mokisenderapp.db.entities.Fact

@Dao
interface FactDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(pokemon: Fact)

}