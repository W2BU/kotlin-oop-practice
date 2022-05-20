package lab6

import lab2.Color
import lab2.ColoredShape2D
import lab2.shapes.*
import lab2.ShapeCollector

fun main() {
    val red = Color(255, 0 ,0)
    val green = Color(0, 255, 0)
    val blue = Color(0, 0, 255)
    val collection = ShapeCollector<ColoredShape2D>()
    collection.addList(
        listOf(
            Rectangle(2.0, 1.0, red, green),
            Square(4.0, green, red),
            Circle(2.0, red, blue),
            Triangle(3.0, 4.0, 5.0, blue, green)
        )
    )
    val serializer = Serializer();
    serializer.serializeToFile(collection, "sample.txt")
    val listOfShapes = serializer.deserializeFromFile("sample.txt")
    val deserializedCollection = ShapeCollector(listOfShapes)
    deserializedCollection.add(Circle(3.0, blue, red))
    deserializedCollection.add(Square(5.0, red, blue))
    serializer.serializeToFile(deserializedCollection, "sample2.txt")
}