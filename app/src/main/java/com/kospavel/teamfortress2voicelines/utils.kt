package com.kospavel.teamfortress2voicelines

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Sound(
    val id: Int,
    val character: Character,
    val resourceName: String,
    var favourite: Boolean = false
)

open class AnyCharacter

abstract class Character : AnyCharacter() {

    @get:StringRes
    abstract val name: Int

    @get:DrawableRes
    abstract val portrait: Int
}

class Scout : Character() {
    override val name = R.string.scout_name
    override val portrait = R.drawable.ic_scout_portrait
}

class Soldier : Character() {
    override val name = R.string.soldier_name
    override val portrait = R.drawable.ic_soldier_portrait
}

class Pyro : Character() {
    override val name = R.string.pyro_name
    override val portrait = R.drawable.ic_pyro_portrait
}

class Demo : Character() {
    override val name = R.string.demo_name
    override val portrait = R.drawable.ic_demo_portrait
}

class Heavy : Character() {
    override val name = R.string.heavy_name
    override val portrait = R.drawable.ic_heavy_portrait
}

class Engineer : Character() {
    override val name = R.string.engineer_name
    override val portrait = R.drawable.ic_engineer_portrait
}

class Sniper : Character() {
    override val name = R.string.sniper_name
    override val portrait = R.drawable.ic_sniper_portrait
}

class Medic : Character() {
    override val name = R.string.medic_name
    override val portrait = R.drawable.ic_medic_portrait
}

class Spy : Character() {
    override val name = R.string.spy_name
    override val portrait = R.drawable.ic_spy_portrait
}

class Announcer : Character() {
    override val name = R.string.announcer_name
    override val portrait = R.drawable.ic_announcer_portrait
}

class Pauling : Character() {
    override val name = R.string.pauling_name
    override val portrait = R.drawable.ic_pauling_portrait
}

class Merasmus : Character() {
    override val name = R.string.merasmus_name
    override val portrait = R.drawable.ic_unexpected_character_portrait
}

class Bomb : Character() {
    override val name = R.string.bomb_name
    override val portrait = R.drawable.ic_unexpected_character_portrait
}

class Blutarch : Character() {
    override val name = R.string.blutarch_name
    override val portrait = R.drawable.ic_unexpected_character_portrait
}

class Redmond : Character() {
    override val name = R.string.redmond_name
    override val portrait = R.drawable.ic_unexpected_character_portrait
}

class UnexpectedCharacter : Character() {
    override val name = R.string.unexpected_name
    override val portrait = R.drawable.ic_unexpected_character_portrait
}

class NoSounds