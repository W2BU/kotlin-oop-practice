package lab2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

import lab2.shapes.*

internal class ShapeCollectorTest {
    private val red = Color(255, 0 ,0)
    private val green = Color(0, 255, 0)
    private val blue = Color(0, 0, 255)

    private val rctngl1 = Rectangle(2.0, 1.0, red, green)
    private val rctngl2 = Rectangle(1.0, 4.0, green, blue)
    private val sqr1 = Square(4.0, green, red)
    private val sqr2 = Square(5.0, red, blue)
    private val crcl1 = Circle(2.0, red, blue)
    private val crcl2 = Circle(3.0, blue, red)
    private val trngl1 = Triangle(3.0, 4.0, 5.0, blue, green)
    private val trngl2 = Triangle(2.0, 2.0, 3.0, green, blue)

    private val fullCollection = ShapeCollector(listOf(rctngl1, rctngl2, sqr1, sqr2, crcl1, crcl2, trngl1, trngl2))
    private val halfCollection = ShapeCollector(listOf(rctngl1, sqr1, crcl1, trngl1))

    @Test
    fun addingElements() {
        val testCollection = ShapeCollector(listOf(rctngl1, sqr1))
        testCollection.add(crcl1)
        testCollection.add(trngl1)

        assertEquals(halfCollection.getContent(), testCollection.getContent())
    }

    @Test
    fun findMaxAreaShape() {
        assertEquals(crcl2 ,fullCollection.findMaxAreaShape())
    }

    @Test
    fun findMinAreaShape() {
        assertEquals(trngl2, fullCollection.findMinAreaShape())
    }

    @Test
    fun areaSum() {
        assertEquals(2.0 + 16.0 + Math.PI * 2 * 4.0 + 6.0, halfCollection.areaSum())
    }

    @Test
    fun findByOutlineColor() {
        assertEquals(listOf(rctngl2, sqr1, trngl2), fullCollection.findByOutlineColor(green))
    }

    @Test
    fun findByFillColor() {
        assertEquals(listOf(rctngl2, sqr2, crcl1, trngl2), fullCollection.findByFillColor(blue))
    }

    @Test
    fun getContent() {
        assertEquals(listOf(rctngl1, sqr1, crcl1, trngl1), halfCollection.getContent())
    }

    @Test
    fun size() {
        assertEquals(4, halfCollection.size())
        assertEquals(8, fullCollection.size())
    }

    @Test
    fun groupByOutlineColor() {
        val groupedByOutlineColor = mapOf(
            red to listOf(rctngl1, crcl1),
            blue to listOf(trngl1),
            green to listOf(sqr1),
        )

        assertEquals(groupedByOutlineColor, halfCollection.groupByOutlineColor())
    }

    @Test
    fun groupByFillColor() {
        val groupedByFillColor = mapOf(
            green to listOf(rctngl1, trngl1),
            red to listOf(sqr1),
            blue to listOf(crcl1),
        )

        assertEquals(groupedByFillColor, halfCollection.groupByFillColor())
    }

    @Test
    fun groupOfClass() {
        assertEquals(listOf(trngl1, trngl2), fullCollection.groupOfClass("Triangle"))
        assertEquals(listOf(rctngl1, rctngl2), fullCollection.groupOfClass("Rectangle"))
        assertEquals(listOf(crcl1, crcl2), fullCollection.groupOfClass("Circle"))
        assertEquals(listOf(sqr1, sqr2), fullCollection.groupOfClass("Square"))
        assertEquals(emptyList<ColoredShape2D>(), fullCollection.groupOfClass("Oval"))
    }
}