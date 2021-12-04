package com.demo.places.presentation.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding
import com.demo.places.R
import com.demo.places.presentation.util.showToast

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {
    lateinit var binding: T

    abstract fun getViewBinding(): T
    abstract fun setupView()
    abstract fun setupEvents()
    abstract fun setupObservers()
    abstract fun getErrorObservers(): List<MutableLiveData<String?>>?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupView()
        setupEvents()
        observeErrors()
        setupObservers()
    }

    private fun observeErrors(){
        getErrorObservers()?.let {
            for (error in it){
                error.observe(this, {
                    showToast(it?:getString(R.string.generic_error_msg))
                })
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}