package com.example.memeapp



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope

import com.bumptech.glide.Glide

import com.example.memeapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit  var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.button.setOnClickListener(){
             getNews()
        }

    }


    private fun getNews(){
      lifecycleScope.launch{
          try {
              val meme : Meme = NewsService.newsInstance.getHeadlines()

              Log.d("MainActivity","gotcha${meme.author}")

              binding.newsTitle.text = meme.title
                    binding.newsDescription.text = meme.author

                    Glide.with(this@MainActivity).load(meme.url).into(binding.newsImage)

          }catch (e: Exception){
              Toast.makeText(applicationContext,"Bruh!! failed!! because ${e.toString()} ",Toast.LENGTH_LONG)
          }

      }
    }



}