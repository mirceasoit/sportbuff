package com.buffup.sdk.model

sealed class Result {
    class Success(val buff: Buff): Result()
    class Error(val message: String?): Result()
}