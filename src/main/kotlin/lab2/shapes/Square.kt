package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D

class Square(
    val h: Double,
    override val outline: Color = Color(),
    override val fill: Color = Color()
) : ColoredShape2D {

    init {
        if (h <= 0) throw IllegalArgumentException("Side can't be negative or zero")
    }

    override fun area(): Double = h * h

    override fun equals(other: Any?): Boolean =
        other is Square &&
        other.h == this.h &&
        other.outline == this.outline &&
        other.fill == this.fill

    override fun hashCode(): Int = h.hashCode()

    override fun toString(): String = "Square: [$h], outline$outline, fill$fill"
}