package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D

class Rectangle(
    val h: Double,
    val w: Double,
    override val outline: Color = Color(),
    override val fill: Color = Color()
) : ColoredShape2D {

    init {
        if (h <= 0 || w <= 0) {
            throw IllegalArgumentException("Sides can't be negative or zero")
        }
    }

    override fun area(): Double =  w * h

    override fun equals(other: Any?): Boolean =
        other is Rectangle &&
        other.h == this.h &&
        other.w == this.w &&
        other.outline == this.outline &&
        other.fill == this.fill

    override fun hashCode(): Int = h.hashCode() * 31 + w.hashCode()

    override fun toString(): String = "Rectangle: [$h, $w], outline$outline, fill$fill"
}