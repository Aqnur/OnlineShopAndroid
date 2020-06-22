package com.example.onlineshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class MainActivity : AppCompatActivity() {

    private val fragmentManager: FragmentManager = supportFragmentManager
    private var activeFragment: Fragment = HomeFragment()
    private var homeFragment: Fragment = HomeFragment()
    private var favouritesFragment: Fragment = FavouriteFragment()
    private var accountFragment: Fragment = AccountFragment()

    private lateinit var bottomNavigation: BottomNavigationViewEx

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        bottomNavAnimations()
        bottomNavigation.onNavigationItemSelectedListener = navListener
        hidingFragments()
    }

    private fun bindViews() {
        bottomNavigation = findViewById(R.id.bottomNavigation)
    }

    private fun bottomNavAnimations() {
        bottomNavigation.setIconSize(33f, 33f)
        bottomNavigation.setTextVisibility(false)
        bottomNavigation.enableItemShiftingMode(false)
        bottomNavigation.enableShiftingMode(false)
        bottomNavigation.enableAnimation(false)
        for (i in 0 until bottomNavigation.menu.size()) {
            bottomNavigation.setIconTintList(i, null)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        bottomNavigation.visibility = View.VISIBLE
    }

    private fun hidingFragments() {
        fragmentManager.beginTransaction().add(R.id.frame, activeFragment).hide(homeFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame, homeFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame, favouritesFragment).hide(favouritesFragment).commit()
        fragmentManager.beginTransaction().add(R.id.frame, accountFragment).hide(accountFragment).commit()
    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_item_home -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment)
                        .commit()
                    activeFragment = homeFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_item_fav -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(favouritesFragment)
                        .commit()
                    activeFragment = favouritesFragment
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_item_acc -> {
                    fragmentManager.beginTransaction().hide(activeFragment).show(accountFragment)
                        .commit()
                    activeFragment = accountFragment
                    return@OnNavigationItemSelectedListener true
                }
            }
            return@OnNavigationItemSelectedListener false
        }
}
