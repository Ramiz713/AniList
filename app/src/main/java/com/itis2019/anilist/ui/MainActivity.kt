package com.itis2019.anilist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis2019.anilist.R
import com.itis2019.anilist.ui.animeList.AnimeListFragment
import com.itis2019.anilist.ui.mangaList.MangaListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AniListTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setViewPager(view_pager)
        tabs.setupWithViewPager(view_pager)
    }

    private fun setViewPager(pager: androidx.viewpager.widget.ViewPager) {
        val adapter = TabPagerAdapter(supportFragmentManager)
        val fragmentOne = AnimeListFragment()
        val fragmentTwo = MangaListFragment()
        adapter.addFragment(fragmentOne, getString(R.string.top_anime))
        adapter.addFragment(fragmentTwo, getString(R.string.top_manga))
        pager.adapter = adapter
    }
}
