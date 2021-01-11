package com.kospavel.teamfortress2voicelines.strategies

import androidx.annotation.IdRes
import com.kospavel.teamfortress2voicelines.*

class CharacterByIdStrategyImpl(@IdRes private val id: Int) : CharacterStrategy {

    private val charactersNames: Map<Int, () -> Character> = mapOf(
        R.id.filter_scout to { Scout() },
        R.id.filter_soldier to { Soldier() },
        R.id.filter_pyro to { Pyro() },
        R.id.filter_demo to { Demo() },
        R.id.filter_heavy to { Heavy() },
        R.id.filter_engineer to { Engineer() },
        R.id.filter_sniper to { Sniper() },
        R.id.filter_medic to { Medic() },
        R.id.filter_spy to { Spy() },
        R.id.filter_announcer to { Announcer() },
        R.id.filter_pauling to { Pauling() },
    )

    override fun character(): AnyCharacter {
        return charactersNames.entries.firstOrNull {
            it.key == id
        }
            ?.value?.invoke()
            ?: AnyCharacter()
    }

}