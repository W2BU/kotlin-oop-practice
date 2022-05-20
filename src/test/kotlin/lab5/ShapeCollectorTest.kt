package lab5

import lab2.Color
import lab2.ColoredShape2D
import lab2.ShapeCollector
import lab5.AreaComparator
import lab5.SquareComparator

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

    private val fullCollection = ShapeCollector(listOf(rctngl1, sqr1, crcl1, trngl1, rctngl2, sqr2, crcl2, trngl2))
    private val halfCollection = ShapeCollector(listOf(rctngl1, sqr1, crcl1, trngl1))

    @Test
    fun addAll() {

        val testCollection = ShapeCollector<ColoredShape2D>()

        testCollection.addAll(listOf(rctngl1, sqr1, crcl1, trngl1))
        assertEquals(halfCollection.shapesList, testCollection.shapesList)

        testCollection.addAll(listOf(rctngl2, sqr2, crcl2, trngl2))

        assertEquals(fullCollection.shapesList, testCollection.shapesList)
    }

    @Test
    fun getSorted() {

        halfCollection.getSorted(AreaComparator())

        val expectedList = listOf(rctngl1, sqr1, crcl1, trngl1)
        assertEquals(halfCollection.shapesList, expectedList)

        val squaresList = listOf(sqr1, sqr2)
        val squareCollection = ShapeCollector(squaresList)
        squareCollection.getSorted(SquareComparator())
        assertEquals(squareCollection.shapesList, squaresList)
    }

    @Test
    fun groupOfClass() {
        assertEquals(fullCollection.groupOfClass<Rectangle>(), listOf(rctngl1, rctngl2))
    }
}