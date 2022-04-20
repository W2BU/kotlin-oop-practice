package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D
import kotlin.math.PI

class Circle(
    val r: Double,
    override val outline: Color = Color(),
    override val fill: Color = Color(),
) : ColoredShape2D {

    init {
        if (r <= 0)
            throw IllegalArgumentException("Radius can't be negative or zero")
    }

    override fun area() = 2 * r * r * PI

    override fun equals(other: Any?): Boolean =
        other is Circle &&
        other.r == this.r &&
        other.outline == this.outline &&
        other.fill == this.fill

    override fun hashCode(): Int = r.hashCode()

    override fun toString(): String = "Circle: [$r], outline$outline, fill$fill"
}