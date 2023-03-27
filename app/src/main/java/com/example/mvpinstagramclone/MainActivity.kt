package com.example.mvpinstagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvpinstagramclone.adapter.BigImageAdapter
import com.example.mvpinstagramclone.adapter.HistoryAdapter
import com.example.mvpinstagramclone.databinding.ActivityMainBinding
import com.example.mvpinstagramclone.model.Photo
import com.example.mvpinstagramclone.presenter.MainPresenterImpl
import com.example.mvpinstagramclone.presenter.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainView {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val historyAdapter by lazy { HistoryAdapter() }
    private val bigImageAdapter by lazy { BigImageAdapter() }
    private lateinit var presenter: MainPresenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        presenter = MainPresenterImpl(this)

        binding.btnGo.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
        binding.rvHistory.apply {
            adapter = historyAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
        }

        binding.rvImages.apply {
            adapter = bigImageAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        GlobalScope.launch(Dispatchers.Main) {
            presenter.getAllImages()
        }
    }

    override fun onGetImagesSuccessListener(photos: List<Photo>) {
        historyAdapter.submitList(photos)
        bigImageAdapter.submitList(photos)
        Toast.makeText(this, photos.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onGetImagesFailureListener(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}