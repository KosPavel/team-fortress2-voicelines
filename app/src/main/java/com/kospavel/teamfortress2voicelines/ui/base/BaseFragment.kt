package com.kospavel.teamfortress2voicelines.ui.base

import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.kospavel.teamfortress2voicelines.ui.generatevoice.GenerateVoiceFragment
import com.kospavel.teamfortress2voicelines.ui.main.MainFragment

open class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    companion object {
        const val TYPE = "type"
        private val instances = mutableListOf<BaseFragment>()

        fun instance(fragmentType: FragmentType): BaseFragment {
            for (instance in instances) {
                if (instance.arguments?.get(TYPE) == fragmentType) {
                    return instance
                }
            }
            val fragment = when (fragmentType) {
                FragmentType.MAIN -> MainFragment()
                FragmentType.FAVOURITES -> MainFragment()
                FragmentType.GENERATE -> GenerateVoiceFragment()
            }.apply {
                arguments = bundleOf(
                    TYPE to fragmentType
                )
            }

            instances.add(fragment)
            return fragment
        }
    }

}

enum class FragmentType {
    MAIN,
    FAVOURITES,
    GENERATE
}