package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D

class Square(_h: Double, _outline: Color = Color(), _fill: Color = Color()) : ColoredShape2D {
    private val h: Double

    override val outline: Color
    override val fill: Color

    init {
        if (_h <= 0) {
            throw IllegalArgumentException("Side can't be negative or zero")
        }
        h = _h

        outline = _outline
        fill = _fill
    }

    override fun area(): Double = h * h

    override fun toString(): String = "Square: [$h], outline$outline, fill$fill"
}