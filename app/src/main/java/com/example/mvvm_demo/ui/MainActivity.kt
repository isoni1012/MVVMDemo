package com.example.mvvm_demo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_demo.adapter.PhotoListAdapter
import com.example.mvvm_demo.data.NetworkResult
import com.example.mvvm_demo.databinding.ActivityMainBinding
import com.example.mvvm_demo.viewmodel.PhotoListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: PhotoListAdapter
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PhotoListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        observer()
    }

    private fun init() {
//        binding.rvPhotos.layoutManager = LinearLayoutManager(this)
        binding.rvPhotos.layoutManager = GridLayoutManager(this,2)
        adapter = PhotoListAdapter(this, arrayListOf())
        binding.rvPhotos.adapter = adapter
        viewModel.getPhotos(0)
    }

    private fun observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.photoListState.collect { result ->
                    when (result) {
                        is NetworkResult.Success -> {
                            if (result.data.photos.isNotEmpty()) {
                                adapter.addData(result.data.photos)
                            }
                        }

                        is NetworkResult.Fail -> {}
                        is NetworkResult.NoInternet -> {}
                        is NetworkResult.Loading -> {}
                    }

                }
            }
        }
    }
}