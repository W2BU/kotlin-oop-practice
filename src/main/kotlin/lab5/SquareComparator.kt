package lab5

import lab2.shapes.Square

internal class SquareComparator : Comparator<Square> {
    override fun compare(sqr1: Square, sqr2: Square): Int {
        return if (sqr1.h == sqr2.h) 0 else if (sqr1.h > sqr2.h) 1 else -1
    }
}