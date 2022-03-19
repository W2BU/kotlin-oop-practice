package lab1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class LibraryClassTest {

    private val fullList: String  =  "1. Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021\n" +
                             "2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2014\n" +
                             "3. Зачем пить, если можно есть? // Едоковн Е.Т. // 1984\n" +
                             "4. Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999\n" +
                             "5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006"
    private val firstOne  = "Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021"
    private val secondOne = "Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2014"
    private val thirdOne  = "Зачем пить, если можно есть? // Едоковн Е.Т. // 1984"
    private val fourthOne = "Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999"
    private val fifthOne  = "Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006"
    private val printedList: String =   "Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021, " +
                                        "Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2014, " +
                                        "Зачем пить, если можно есть? // Едоковн Е.Т. // 1984, " +
                                        "Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999, " +
                                        "Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006"

    @Test
    fun addBooks() {
        val sampleLib = Library()
        assertEquals(0, sampleLib.getSize())
        assertEquals("", sampleLib.toString())

        sampleLib.addBooks("1. Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021")
        assertEquals(1, sampleLib.getSize())
        assertEquals(firstOne, sampleLib.toString())

        sampleLib.addBooks(
                "2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2014\n" +
                "3. Зачем пить, если можно есть? // Едоковн Е.Т. // 1984\n" +
                "4. Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999\n" +
                "5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006")
        assertEquals(5, sampleLib.getSize())
        assertEquals(printedList, sampleLib.toString())
    }

    @Test
    fun oldestBook() {
        val sampleLib = Library()
        assertNull(sampleLib.oldestBook())

        sampleLib.addBooks(fullList)
        assertEquals(thirdOne, sampleLib.oldestBook().toString())
    }

    @Test
    fun latestBook() {
        val sampleLib = Library()
        assertNull(sampleLib.latestBook())

        sampleLib.addBooks(fullList)
        assertEquals(firstOne, sampleLib.latestBook().toString())
    }

    @Test
    fun longestNameBook() {
        var sampleLib = Library()
        assertNull(sampleLib.longestNameBook())

        sampleLib.addBooks(thirdOne)
        sampleLib.addBooks(fourthOne)
        assertEquals(thirdOne, sampleLib.longestNameBook().toString())

        sampleLib = Library()
        sampleLib.addBooks(fullList)
        assertEquals(fifthOne, sampleLib.longestNameBook().toString())
    }

    @Test
    fun shortestNameBook() {
        var sampleLib = Library()
        assertNull(sampleLib.shortestNameBook())

        sampleLib.addBooks(thirdOne)
        sampleLib.addBooks(fourthOne)
        assertEquals(thirdOne, sampleLib.shortestNameBook().toString())

        sampleLib = Library()
        sampleLib.addBooks(fullList)
        assertEquals(firstOne, sampleLib.shortestNameBook().toString())
    }
}