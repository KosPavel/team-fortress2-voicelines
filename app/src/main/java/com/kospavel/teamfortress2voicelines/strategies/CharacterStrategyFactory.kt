package com.kospavel.teamfortress2voicelines.strategies

import android.content.Context
import androidx.annotation.IdRes

class CharacterStrategyFactory {
    companion object {
        fun strategy(context: Context, filename: String): CharacterStrategy {
            return CharacterByNameStrategyImpl(context, filename)
        }

        fun strategy(@IdRes id: Int): CharacterStrategy {
            return CharacterByIdStrategyImpl(id)
        }
    }
}