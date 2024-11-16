package com.delacruz.nicolas.laboratoriocalificado03

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    fun getTeachers(): Call<List<Teacher>>
}
