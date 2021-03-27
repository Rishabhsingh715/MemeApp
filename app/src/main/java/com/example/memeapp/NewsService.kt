package com.example.memeapp



import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET





const val BASE_URL = "https://meme-api.herokuapp.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()              //Creates a Retrofit object.
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NewsInterface {

    @GET("/gimme") //end point
   suspend fun getHeadlines(): Meme
}

object NewsService {      //generates an implementation of the MarsApiService....
    val newsInstance : NewsInterface by lazy {  //....interface using the retrofit object.
        retrofit.create(NewsInterface::class.java)
    }
}
