package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D
import kotlin.math.sqrt

class Triangle(_a: Double, _b: Double, _c: Double, _outline: Color = Color(), _fill: Color = Color()) : ColoredShape2D {
    private val a: Double
    private val b: Double
    private val c: Double

    override val outline: Color
    override val fill: Color

    init {
        if (_a <= 0 || _b <= 0 || _c <= 0)
            throw IllegalArgumentException("Sides can't be negative or zero")
        if (((_a + _b) <= _c) ||
            ((_b + _c) <= _a) ||
            ((_a + _c) <= _b)
        )
            throw IllegalArgumentException("Triangle does not exist")
        a = _a
        b = _b
        c = _c

        outline = _outline
        fill = _fill
    }

    override fun area(): Double {
        val p = (a + b + c) / 2
        return sqrt(p * (p - a) * (p - b) * (p - c))
    }

    override fun toString(): String = "Triangle: [$a, $b, $c], outline$outline, fill$fill"
}