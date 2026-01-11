package com.example.orufy.data.remote

import com.example.orufy.data.local.entity.UrlHistoryEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface ApiService {
    @POST("upload")
    suspend fun uploadHistory(
        @Body history: List<UrlHistoryEntity>
    ): Response<Unit>

}