package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D
import kotlin.math.PI

class Circle(_r: Double, _outline: Color = Color(), _fill: Color = Color()) : ColoredShape2D {
    private val r: Double

    override val outline: Color
    override val fill: Color

    init {
        if (_r <= 0)
            throw IllegalArgumentException("Radius can't be negative or zero")
        r = _r

        outline = _outline
        fill = _fill
    }

    override fun area() = 2 * r * r * PI

    override fun toString(): String = "Circle: [$r], outline$outline, fill$fill"
}