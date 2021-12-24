package com.example.mokisenderapp.data.retrofit

import com.example.mokisenderapp.db.entities.Fact
import retrofit2.http.GET

interface RetrofitService {

    @GET("fact")
    suspend fun getFact(): Fact
}