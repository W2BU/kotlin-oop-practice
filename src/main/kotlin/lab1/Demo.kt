package lab1

fun main() {
    val demoBookList =
                "1. Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021\n" +
                "2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2014\n" +
                "3. Зачем пить, если можно есть? // Едоковн Е.Т. // 1984\n" +
                "4. Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999\n" +
                "5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006"

    val myLib = Library()
    myLib.addBooks(demoBookList)
    println("Latest Book: ${myLib.latestBook()}")
    println("Oldest Book: ${myLib.oldestBook()}")
    println("Longest Name Book: ${myLib.longestNameBook()}")
    println("Shortest Name Book: ${myLib.shortestNameBook()}")
    println("Library size: ${myLib.getSize()}")
    println()
    myLib.prettyPrint()
}
