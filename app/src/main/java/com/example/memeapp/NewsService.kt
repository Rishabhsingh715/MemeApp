package com.example.memeapp



import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET





const val BASE_URL = "https://meme-api.herokuapp.com"



private val retrofit = Retrofit.Builder()              //Creates a Retrofit object.
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NewsInterface {

    @GET("/gimme") //end point
    fun getHeadlines(): Call<Meme>
}

object NewsService {      //generates an implementation of the MarsApiService....
    val newsInstance : NewsInterface by lazy {  //....interface using the retrofit object.
        retrofit.create(NewsInterface::class.java)
    }
}
