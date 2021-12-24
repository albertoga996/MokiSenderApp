package com.example.mokisenderapp.data.ppal

import com.example.mokisenderapp.data.APIResult
import com.example.mokisenderapp.db.FactDao
import com.example.mokisenderapp.db.entities.Fact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PpalRepository @Inject constructor(
    private val ppalSource: PpalSource,
    private val localSource: FactDao
) {

    suspend fun getFact(): APIResult<Fact> {
        val result = ppalSource.getFact()
        if (result is APIResult.Success) {
            localSource.insert(result.data)
        }
        return result
    }
}