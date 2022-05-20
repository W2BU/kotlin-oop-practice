package lab5

import lab2.shapes.*
import lab2.Color
import lab2.ShapeCollector


fun main() {
    val myRectangle = Rectangle(3.0, 4.0, Color(4, 255, 144), Color(255,89,14,18))
    println(myRectangle)
    val myCircle = Circle(15.0, Color(4, 255, 144))
    println(myCircle)
    val myTriangle = Triangle(3.0, 4.0, 5.0, fill = Color(244, 143, 234))
    println(myTriangle)
    val mySquare = Square(8.0, fill = Color(244, 143, 234))
    println(mySquare)

    val shapeCollection = ShapeCollector(listOf(myCircle, myRectangle, mySquare, myTriangle))
    println("Max area shape: ${shapeCollection.findMaxAreaShape()}")
    println("Min area shape: ${shapeCollection.findMinAreaShape()}")
    println("Sum of all areas: ${shapeCollection.areaSum()}")
    println("Found by outline color: ${shapeCollection.findByOutlineColor(Color(4, 255, 144))}")
    println("Found by fill color: ${shapeCollection.findByFillColor(Color(244, 143, 234))}")
    println("Content:\n${shapeCollection.getContent()}")
    println("Size: ${shapeCollection.size()}")
    println("Grouped by outline color:\n${shapeCollection.groupByOutlineColor()}")
    println("Grouped by fill color:\n${shapeCollection.groupByFillColor()}")
    println("Grouped by type:\n${shapeCollection.groupOfClass<Rectangle>()}")
}