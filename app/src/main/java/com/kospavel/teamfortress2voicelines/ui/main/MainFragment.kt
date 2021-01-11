package com.kospavel.teamfortress2voicelines.ui.main

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.widget.PopupMenu
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kospavel.teamfortress2voicelines.App
import com.kospavel.teamfortress2voicelines.NoSounds
import com.kospavel.teamfortress2voicelines.R
import com.kospavel.teamfortress2voicelines.strategies.CharacterStrategyFactory
import com.kospavel.teamfortress2voicelines.ui.base.BaseFragment
import com.kospavel.teamfortress2voicelines.ui.base.FragmentType
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment(R.layout.fragment_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val vm: MainFragmentViewModel by lazy {
        viewModels<MainFragmentViewModel> { viewModelFactory }
            .value
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.get(requireContext()).appComponent.inject(this)

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        val adapter = MainAdapter { vm.save(it) }
        main_recycler_view.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
            this.setHasFixedSize(true)
        }

        vm.sounds.observe(viewLifecycleOwner) {
            if (arguments?.get(TYPE) == FragmentType.MAIN) {
                adapter.items = it
            } else if (arguments?.get(TYPE) == FragmentType.FAVOURITES) {
                adapter.items = it.filter { sound ->
                    sound.favourite
                }
            }
            if (adapter.items.isEmpty()) {
                adapter.items = listOf(NoSounds())
            }
            adapter.notifyDataSetChanged()
        }

        search_et.addTextChangedListener {
            vm.filter.value = Pair(vm.filter.value!!.first, it.toString())
        }

        PopupMenu(
            ContextThemeWrapper(requireContext(), R.style.FilterPopupMenu),
            filter_by_character
        ).apply {
            inflate(R.menu.filter_by_character_popup_menu)
        }.let { menu ->
            filter_by_character.setOnClickListener {
                menu.show()
            }
            menu.setOnMenuItemClickListener {
                val search = if (search_et.text.isNotEmpty()) search_et.text.toString() else ""
                vm.filter.value =
                    Pair(
                        CharacterStrategyFactory.strategy(it.itemId).character(),
                        search
                    )
                true
            }
        }
    }
}