package lab2

import kotlinx.serialization.*

@Serializable
data class Color(val r: Short = 0, val g: Short = 0, val b: Short = 0, val a: Short = 100) {


    init {
        require(r in 0..255) {"R component must be in range from 0 to 255"}
        require(g in 0..255) {"G component must be in range from 0 to 255"}
        require(b in 0..255) {"B component must be in range from 0 to 255"}
        require(a in 0..100) {"A component must be in range from 0 to 100"}
    }

    override fun toString(): String = "[$r, $g, $b, $a]"
}
