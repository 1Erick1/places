package com.demo.places.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.demo.places.R
import com.demo.places.presentation.util.showToast

abstract class BaseFragment<T: ViewBinding>: Fragment() {
    lateinit var binding: T
    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): T
    abstract fun setupView()
    abstract fun setupEvents()
    abstract fun setupObservers()
    abstract fun getErrorObservers(): List<MutableLiveData<Exception>>?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding(inflater,container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupEvents()
        observeErrors()
        setupObservers()
    }

    private fun observeErrors(){
        getErrorObservers()?.let {
            for (error in it){
                error.observe(viewLifecycleOwner, Observer {
                    requireActivity().showToast(getString(R.string.generic_error_msg))
                })
            }
        }
    }
}