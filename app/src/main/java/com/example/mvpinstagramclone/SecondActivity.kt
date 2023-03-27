package com.example.mvpinstagramclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvpinstagramclone.adapter.LittleImageAdapter
import com.example.mvpinstagramclone.databinding.ActivitySecondBinding
import com.example.mvpinstagramclone.model.Photo
import com.example.mvpinstagramclone.presenter.MainPresenterImpl
import com.example.mvpinstagramclone.presenter.MainView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity(), MainView {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    private val littleImageAdapter by lazy { LittleImageAdapter() }
    private lateinit var presenter: MainPresenterImpl
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.hide()
        presenter = MainPresenterImpl(this)
        binding.rvImages.apply {
            adapter = littleImageAdapter
            layoutManager = GridLayoutManager(this@SecondActivity, 3)
        }
        GlobalScope.launch (Dispatchers.Main){
            presenter.getAllImages()
        }
    }

    override fun onGetImagesSuccessListener(photos: List<Photo>) {
        littleImageAdapter.submitList(photos)
        Toast.makeText(this, photos.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onGetImagesFailureListener(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}