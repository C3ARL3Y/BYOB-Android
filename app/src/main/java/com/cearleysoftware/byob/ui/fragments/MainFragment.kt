package com.cearleysoftware.byob.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.cearleysoftware.byob.R
import com.cearleysoftware.byob.extensions.inflateTo
import com.cearleysoftware.byob.extensions.safeActivity
import com.cearleysoftware.byob.ui.activities.MainActivity
import com.cearleysoftware.byob.ui.fragments.alex.AlexFragment
import com.cearleysoftware.byob.ui.fragments.customize.CustomizeFragment
import com.cearleysoftware.byob.ui.fragments.favorites.FavoritesFragment
import com.cearleysoftware.byob.ui.fragments.picks.BaristaPicksFragment
import com.cearleysoftware.byob.ui.viewmodels.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

//  Copyright © 2019 Cearley Software. All rights reserved.

class MainFragment: Fragment() {

    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflateTo(R.layout.main_fragment, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager(viewpager)
        setupBottomNavigation(bottomNavigation, viewpager)

        val mainActivity = safeActivity as MainActivity
        setupToolbar(mainActivity, toolbar, toolbarTitle)
        mainViewModel.customDrinkData.clear()
    }

    private fun setupToolbar(mainActivity: MainActivity, toolbar: Toolbar, toolbarTitle: TextView) {
        mainActivity.setSupportActionBar(toolbar)
        mainActivity.supportActionBar?.run {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(false)
        }
        toolbarTitle.text = resources.getText(R.string.barista_picks)
        mainViewModel.hasFavoriteDrinkToSave.observe(this, Observer { hasDrinkToSave ->
            addToFavoriteButton.visibility = if(hasDrinkToSave) View.VISIBLE else View.GONE
        })
    }

    private fun setupBottomNavigation(bottomNavigation: BottomNavigationView, viewPager: ViewPager) {
        bottomNavigation.apply {
            itemIconTintList = null
            setOnNavigationItemSelectedListener { menuItem ->
                val id = menuItem.itemId
                when(id){

                    R.id.action_picks -> viewPager.currentItem = 0

                    R.id.action_customize -> viewPager.currentItem = 1

                    R.id.action_favorites -> viewPager.currentItem = 2

                    R.id.action_alex -> viewPager.currentItem = 3

                }
                true
            }
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val res = context?.resources ?: return
        val adapter = Adapter(childFragmentManager).apply {
            addFragment(
                    fragment = BaristaPicksFragment(),
                    title = res.getString(R.string.barista_picks)
            )
            addFragment(
                    fragment = CustomizeFragment(),
                    title = res.getString(R.string.customize)
            )
            addFragment(
                    fragment = FavoritesFragment(),
                    title = res.getString(R.string.favorites)
            )

            addFragment(
                    fragment = AlexFragment(),
                    title = res.getString(R.string.alex_moe)
            )

        }
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                toolbarTitle.text = adapter.getPageTitle(position)
                when(position){

                    0 -> bottomNavigation.selectedItemId = R.id.action_picks

                    1 -> bottomNavigation.selectedItemId = R.id.action_customize

                    2 -> bottomNavigation.selectedItemId = R.id.action_favorites

                    3 -> bottomNavigation.selectedItemId = R.id.action_alex
                }
            }

        })
        viewPager.setCurrentItem(0, false)
    }

    internal class Adapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val fragments = ArrayList<Fragment>()
        private val titles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            titles.add(title)
        }

        override fun getItem(position: Int) = fragments[position]

        override fun getCount() = fragments.size

        override fun getPageTitle(position: Int) = titles[position]
    }

}