package lab2.shapes

import lab2.Color
import lab2.ColoredShape2D
import kotlin.math.sqrt

class Triangle(
    val a: Double,
    val b: Double,
    val c: Double,
    override val outline: Color = Color(),
    override val fill: Color = Color()
) : ColoredShape2D {


    init {
        if (a <= 0 || b <= 0 || c <= 0)
            throw IllegalArgumentException("Sides can't be negative or zero")
        if (((a + b) <= c) ||
            ((b + c) <= a) ||
            ((a + c) <= b))
            throw IllegalArgumentException("Triangle does not exist")
    }

    override fun area(): Double {
        val p = (a + b + c) / 2
        return sqrt(p * (p - a) * (p - b) * (p - c))
    }

    override fun equals(other: Any?): Boolean =
        other is Triangle &&
        other.a == this.a &&
        other.b == this.b &&
        other.c == this.c &&
        other.outline == this.outline &&
        other.fill == this.fill

    override fun hashCode(): Int = a.hashCode() * 31 * 31 + b.hashCode() * 31 + c.hashCode()


    override fun toString(): String = "Triangle: [$a, $b, $c], outline$outline, fill$fill"
}