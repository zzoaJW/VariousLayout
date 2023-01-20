package com.z0o0a.variouslayout.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.z0o0a.variouslayout.viewmodel.MainActivityVM
import com.z0o0a.variouslayout.R
import com.z0o0a.variouslayout.adapter.RecyclerviewAdapter
import com.z0o0a.variouslayout.databinding.ActivityMainBinding
import java.lang.Integer.max

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityVM
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainActivityVM::class.java]
        binding.vm = viewModel

        binding.lifecycleOwner = this

        // 리사이클러뷰
        val adapter = RecyclerviewAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.scrollToPosition(Int.MAX_VALUE/2)

        val sh = PagerSnapHelper()
        sh.attachToRecyclerView(binding.recyclerview)

        binding.recyclerview.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)

        viewModel.getAll().observe(this, Observer {
            adapter.setDrinkList(it)
        })

        // 4초마다 배너 넘기기
        val handler = Handler(Looper.getMainLooper())

        handler.post(object: Runnable{
            override fun run(){
                binding.recyclerview.smoothScrollToPosition((binding.recyclerview.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()+1)

                // 4초마다 반복
                handler.postDelayed(this, 4000)
            }
        })
    }
}