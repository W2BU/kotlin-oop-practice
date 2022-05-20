package lab6

import lab2.Color
import lab2.ShapeCollector
import lab2.shapes.Circle
import lab2.shapes.Rectangle
import lab2.shapes.Square
import lab2.shapes.Triangle
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.FileNotFoundException
import java.io.IOException

internal class SerializerTest {

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
    fun throwsWhenCantOpenFile() {
        val serializer = Serializer()
        assertThrows(IllegalArgumentException::class.java) {
            serializer.deserializeFromFile("nofile.txt")
        }
    }

    @Test
    fun serializeFilePairTest() {
        val serializer = Serializer()
        serializer.serializeToFile(halfCollection, "seTest.txt")
        val deserializedList = serializer.deserializeFromFile("seTest.txt")
        assertEquals(halfCollection.shapesList, deserializedList)
    }

    @Test
    fun serializeJsonPairTest() {
        val serializer = Serializer()
        val serializedString = serializer.serializeToJson(halfCollection)
        val deserializedList = serializer.deserializeFromJson(serializedString)
        assertEquals(halfCollection.shapesList, deserializedList)
    }
}