package com.buffup.sdk.repository

import com.buffup.sdk.api.BuffApi
import com.buffup.sdk.model.Result

class BuffRepository {
    suspend fun loadBuff(buffId: Long): Result {
        return try {
            val result = BuffApi.retrofitService.loadBuff(buffId)
            Result.Success(result.result)
        } catch (throwable: Throwable) {
            Result.Error(throwable.message)
        }
    }
}