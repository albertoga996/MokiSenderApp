package com.example.mokisenderapp.data.ppal

import com.example.mokisenderapp.data.APIResult
import com.example.mokisenderapp.data.retrofit.RetrofitService
import com.example.mokisenderapp.db.entities.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class PpalSource @Inject constructor(
    private val retrofitService: RetrofitService
) {

    suspend fun getFact(): APIResult<Fact> = withContext(Dispatchers.IO) {
        try {
            APIResult.Success(retrofitService.getFact())
        } catch (e: HttpException) {
            APIResult.Error(e.message())
        }
    }
}