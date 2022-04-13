package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D

class Rectangle(_h: Double, _w: Double, _outline: Color = Color(), _fill: Color = Color()) : ColoredShape2D {
    private val h: Double
    private val w: Double

    override val outline: Color
    override val fill: Color

    init {
        if (_h <= 0 || _w <= 0) {
            throw IllegalArgumentException("Sides can't be negative or zero")
        }
        h = _h
        w = _w

        outline = _outline
        fill = _fill
    }

    override fun area(): Double =  w * h

    override fun toString(): String = "Rectangle: [$h, $w], outline$outline, fill$fill"
}