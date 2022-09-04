package com.example.dogedex.doglist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dogedex.api.ApiResponseStatus
import com.example.dogedex.databinding.ActivityDogListBinding
import com.example.dogedex.dogdetail.DogDetailActivity
import com.example.dogedex.dogdetail.DogDetailActivity.Companion.DOG_KEY
private const val GRID_SPAN_COUNT = 3
class DogListActivity : AppCompatActivity() {


    private val dogViewModel : DogListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loadingWheel = binding.loadingWheel



        val recycler = binding.dogRecycler
        recycler.layoutManager = GridLayoutManager(this, GRID_SPAN_COUNT)

        val adapter = DogAdapter()

        adapter.setOnItemClickLister {
            intent = Intent(this,DogDetailActivity::class.java)
            intent.putExtra(DOG_KEY,it)
            startActivity(intent)
        }

        adapter.setonLongItemClickLister {
            dogViewModel.addDogToUser(it.id)
        }

        recycler.adapter = adapter

        dogViewModel.doglist.observe(this) {
            adapter.submitList(it)
        }

        dogViewModel.status.observe(this){
            status ->
            when(status){
                is ApiResponseStatus.Error -> {
                    loadingWheel.visibility = View.GONE
                    Toast.makeText(this, status.messageId, Toast.LENGTH_SHORT).show()
                }
                is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
            }

        }

    }

}