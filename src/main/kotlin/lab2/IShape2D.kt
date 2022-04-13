package lab2

import kotlin.math.PI
import kotlin.math.sqrt

interface Shape2D {
    fun area(): Double
}

interface ColoredShape2D : Shape2D {
    val outline: Color
    val fill: Color
}






