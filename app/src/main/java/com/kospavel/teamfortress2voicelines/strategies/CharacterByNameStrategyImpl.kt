package com.kospavel.teamfortress2voicelines.strategies

import android.content.Context
import com.kospavel.teamfortress2voicelines.*

class CharacterByNameStrategyImpl(context: Context, private val filename: String) :
    CharacterStrategy {

    private val charactersNames: Map<String, () -> Character> = mapOf(
        context.getString(R.string.scout_name) to { Scout() },
        context.getString(R.string.soldier_name) to { Soldier() },
        context.getString(R.string.pyro_name) to { Pyro() },
        context.getString(R.string.demo_name) to { Demo() },
        context.getString(R.string.heavy_name) to { Heavy() },
        context.getString(R.string.engineer_name) to { Engineer() },
        context.getString(R.string.sniper_name) to { Sniper() },
        context.getString(R.string.medic_name) to { Medic() },
        context.getString(R.string.spy_name) to { Spy() },
        context.getString(R.string.announcer_name) to { Announcer() },
        context.getString(R.string.pauling_name) to { Pauling() },
        context.getString(R.string.merasmus_name) to { Merasmus() },
        context.getString(R.string.bomb_name) to { Bomb() },
        context.getString(R.string.redmond_name) to { Redmond() },
        context.getString(R.string.blutarch_name) to { Blutarch() },
    )

    override fun character(): Character {
        return charactersNames.entries.firstOrNull {
            filename.split("_")[0].contains(
                it.key,
                ignoreCase = true
            )
        }
            ?.value?.invoke()
            ?: UnexpectedCharacter()
    }

}