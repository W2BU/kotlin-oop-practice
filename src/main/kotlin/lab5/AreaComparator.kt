package lab5

import lab2.ColoredShape2D

class AreaComparator : Comparator<ColoredShape2D> {
    override fun compare(shape1: ColoredShape2D, shape2: ColoredShape2D): Int {
        return if (shape1.area() == shape2.area()) 0 else if (shape1.area() > shape2.area()) 1 else -1
    }
}