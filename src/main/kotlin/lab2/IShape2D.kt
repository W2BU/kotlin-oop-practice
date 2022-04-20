package lab2

interface Shape2D {
    fun area(): Double
}

interface ColoredShape2D : Shape2D {
    val outline: Color
    val fill: Color
}






