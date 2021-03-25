package com.example.memeapp



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.databinding.DataBindingUtil

import com.bumptech.glide.Glide

import com.example.memeapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit  var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener(){
             getNews()
        }

    }

    private fun getNews() {
        val news : Call<Meme> = NewsService.newsInstance.getHeadlines()

        news.enqueue(object : Callback<Meme>{

            override fun onResponse(call: Call<Meme>, response: Response<Meme>) {
                val meme = response.body()
                if(meme != null){
                    Log.d("MainActivity","gotcha${meme.author}")
//                    adapter = NewsAdapter(this@MainActivity,meme)
//                    binding.newsList.adapter = adapter
//
//                    binding.newsList.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.newsTitle.text = meme.title
                    binding.newsDescription.text = meme.author

                    Glide.with(this@MainActivity).load(meme.url).into(binding.newsImage)

                }
            }

            override fun onFailure(call: Call<Meme>, t: Throwable) {
                Log.d("MainActivity","failed bruh!!")
            }

        } )
    }

}