package com.kospavel.teamfortress2voicelines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kospavel.teamfortress2voicelines.ui.FragmentType
import com.kospavel.teamfortress2voicelines.ui.MainFragment
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

        openFragment(MainFragment.instance(FragmentType.MAIN))

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_main -> {
                    openFragment(MainFragment.instance(FragmentType.MAIN))
                    true
                }
                R.id.navigation_favourites -> {
                    openFragment(MainFragment.instance(FragmentType.FAVOURITES))
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}