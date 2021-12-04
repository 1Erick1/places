package com.demo.places.presentation.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.demo.places.R
import com.demo.places.databinding.ActivityMainBinding
import com.demo.places.presentation.base.BaseActivity
import com.demo.places.presentation.base.BaseFragment
import com.demo.places.presentation.fragment.FavoritePlacesFragment
import com.demo.places.presentation.fragment.SearchPlaceFragment

class MainActivity: BaseActivity<ActivityMainBinding>() {
    private val searchFragment = SearchPlaceFragment()
    private val favoriteFragment = FavoritePlacesFragment()
    private var currentFragment: BaseFragment<*>? = null

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupView() {
        with(binding){
            showFragment(searchFragment)
            currentFragment = searchFragment
            navView.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.menu_nearby->{
                        if (currentFragment!=searchFragment){
                            hideFragment(favoriteFragment)
                            showFragment(searchFragment)
                            currentFragment = searchFragment
                        }
                    }
                    R.id.menu_favorite->{
                        if (currentFragment!=favoriteFragment){
                            hideFragment(searchFragment)
                            showFragment(favoriteFragment)
                            currentFragment = favoriteFragment
                        }
                    }
                }
                true
            }
        }
    }

    private fun showFragment(fragment: Fragment){
        if (fragment.isAdded.not()){
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,fragment).commit()
        }else{
            supportFragmentManager.beginTransaction().show(fragment).commit()
        }
    }

    private fun hideFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().hide(fragment).commit()
    }

    override fun setupEvents() {
    }

    override fun setupObservers() {
    }

    override fun getErrorObservers(): List<MutableLiveData<String?>>? {
        return null
    }
}