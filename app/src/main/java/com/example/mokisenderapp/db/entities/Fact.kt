package com.example.mokisenderapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mokisenderapp.Defs
import com.google.gson.annotations.SerializedName

@Entity(tableName = Defs.FACT_TABLE_NAME)
data class Fact(
    @SerializedName("fact")
    @ColumnInfo(name = "fact_desc")
    @PrimaryKey
    var fact: String,

    @SerializedName("length")
    @ColumnInfo(name = "fact_length")
    var length: Int
)
