package lab5

import lab2.Color
import lab2.ColoredShape2D

class ShapeCollector<T : ColoredShape2D>(initial: List<T> = emptyList()) {

    private val content: MutableList<T> = mutableListOf()

    var shapesList: List<T> = emptyList()
        private set
        get() = content.toList()

    init {
        content.addAll(initial)
    }

    fun add(shape: T) {
        content.add(shape)
    }

    fun findMaxAreaShape(): T? = content.maxByOrNull { it.area() }

    fun findMinAreaShape(): T? = content.minByOrNull { it.area() }

    // Returns sum of the all shapes areas
    fun areaSum(): Double = content.sumOf { it.area() }

    // Returns all the shapes matching given outline color
    fun findByOutlineColor(outlineColor: Color): List<T> = content.filter { it.outline == outlineColor}

    // Returns all the shapes matching given fill color
    fun findByFillColor(fillColor: Color): List<T> = content.filter { it.fill == fillColor}

    fun getContent(): List<T> = content.toList()

    fun size(): Int = content.size

    fun groupByOutlineColor(): Map<Color, List<T>> = content.groupBy { it.outline }

    fun groupByFillColor(): Map<Color, List<T>> = content.groupBy { it.fill }

    // Returns all the shapes matching the given type
    inline fun <reified Type : T> groupOfClass(): List<Type> = shapesList.filterIsInstance<Type>()

    fun addAll(shapesList: List<T>) = content.addAll(shapesList)

    fun getSorted(comparator: Comparator<T>): List<T> = content.sortedWith(comparator)

    override fun toString(): String = "$content".replace("[", "").replace("]", "")
}
