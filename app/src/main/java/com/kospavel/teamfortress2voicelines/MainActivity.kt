package com.kospavel.teamfortress2voicelines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kospavel.teamfortress2voicelines.ui.base.BaseFragment
import com.kospavel.teamfortress2voicelines.ui.base.FragmentType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(BaseFragment.instance(FragmentType.MAIN))

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_main -> {
                    openFragment(BaseFragment.instance(FragmentType.MAIN))
                    true
                }
                R.id.navigation_favourites -> {
                    openFragment(BaseFragment.instance(FragmentType.FAVOURITES))
                    true
                }
                R.id.navigation_generate -> {
                    openFragment(BaseFragment.instance(FragmentType.GENERATE))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}