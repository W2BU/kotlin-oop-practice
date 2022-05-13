package lab2

import kotlinx.serialization.*

class ShapeCollector(initial: List<ColoredShape2D> = emptyList()) {

    private val content: MutableList<ColoredShape2D> = mutableListOf()

    init {
        content.addAll(initial)
    }

    fun add(shape: ColoredShape2D) {
        content.add(shape)
    }

    fun findMaxAreaShape(): ColoredShape2D? = content.maxByOrNull { it.area() }

    fun findMinAreaShape(): ColoredShape2D? = content.minByOrNull { it.area() }

    // Returns sum of the all shapes areas
    fun areaSum(): Double = content.sumOf { it.area() }

    // Returns all the shapes matching given outline color
    fun findByOutlineColor(outlineColor: Color): List<ColoredShape2D> = content.filter { it.outline == outlineColor}

    // Returns all the shapes matching given fill color
    fun findByFillColor(fillColor: Color): List<ColoredShape2D> = content.filter { it.fill == fillColor}

    fun getContent(): List<ColoredShape2D> = content.toList()

    fun size(): Int = content.size

    fun groupByOutlineColor(): Map<Color, List<ColoredShape2D>> = content.groupBy { it.outline }

    fun groupByFillColor(): Map<Color, List<ColoredShape2D>> = content.groupBy { it.fill }

    // Returns all the shapes matching the given type
    fun groupOfClass(className: String): List<ColoredShape2D> = content.filter { it.javaClass.simpleName == className }

    override fun toString(): String = "$content".replace("[", "").replace("]", "")
}
