package lab1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class LibraryClassTest {

    private val fullList: String  =  "1. Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021\n" +
                             "2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2006\n" +
                             "3. Зачем пить, если можно есть? // Едоковн Е.Т. // 1984\n" +
                             "4. Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999\n" +
                             "5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006"
    private val firstOne  = Book("Я, мы, все", listOf("Посмотрим Ч.У.", "Тьпоз Ж.Е."), 2021)
    private val secondOne = Book("Как тут быть?", listOf("Тамнавер Х.У.", "Кто Т.О."), 2006)
    private val thirdOne  = Book("Зачем пить, если можно есть?", listOf("Едоковн Е.Т."), 1984)
    private val fourthOne = Book("Кому нужны художники без нот", listOf("Зачемнапис А.Н.", "Количест В.О.", "Необозначе Н.О."), 1999)
    private val fifthOne  = Book("Мечтают ли электроовцы об андроидах?", listOf("Явкни Г.Е."), 2006)
    private val fullLib = listOf(firstOne, secondOne, thirdOne, fourthOne, fifthOne)

    @Test
    fun addBooks() {
        val sampleLib = Library()
        val emptyLib: List<Book> = emptyList()
        assertEquals(0, sampleLib.getSize())
        assertEquals(emptyLib, sampleLib.getBooks())

        sampleLib.addBooks("")
        assertEquals(0, sampleLib.getSize())
        assertEquals(emptyLib, sampleLib.getBooks())

        val oneBookLib = listOf(firstOne)
        sampleLib.addBooks("1. Я, мы, все // Посмотрим Ч.У., Тьпоз Ж.Е. // 2021")
        assertEquals(1, sampleLib.getSize())
        assertEquals(oneBookLib, sampleLib.getBooks())

        sampleLib.addBooks(
                "2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2006\n" +
                "3. Зачем пить, если можно есть? // Едоковн Е.Т. // 1984\n" +
                "4. Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999\n" +
                "5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006")
        assertEquals(5, sampleLib.getSize())
        assertEquals(fullLib, sampleLib.getBooks())
    }

    @Test
    fun oldestBook() {
        var sampleLib = Library()
        assertNull(sampleLib.oldestBook())

        sampleLib.addBooks("2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2006")
        sampleLib.addBooks("5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006")
        assertEquals(secondOne, sampleLib.oldestBook())

        sampleLib = Library()
        sampleLib.addBooks(fullList)
        assertEquals(thirdOne, sampleLib.oldestBook())
    }

    @Test
    fun latestBook() {
        var sampleLib = Library()
        assertNull(sampleLib.latestBook())

        sampleLib.addBooks("2. Как тут быть? // Тамнавер Х.У., Кто Т.О. // 2006")
        sampleLib.addBooks("5. Мечтают ли электроовцы об андроидах? // Явкни Г.Е. // 2006")
        assertEquals(secondOne, sampleLib.latestBook())

        sampleLib = Library()
        sampleLib.addBooks(fullList)
        assertEquals(firstOne, sampleLib.latestBook())
    }

    @Test
    fun longestNameBook() {
        var sampleLib = Library()
        assertNull(sampleLib.longestNameBook())

        sampleLib.addBooks("Зачем пить, если можно есть? // Едоковн Е.Т. // 1984")
        sampleLib.addBooks("Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999")
        assertEquals(thirdOne, sampleLib.longestNameBook())

        sampleLib = Library()
        sampleLib.addBooks(fullList)
        assertEquals(fifthOne, sampleLib.longestNameBook())
    }

    @Test
    fun shortestNameBook() {
        var sampleLib = Library()
        assertNull(sampleLib.shortestNameBook())

        sampleLib.addBooks("Зачем пить, если можно есть? // Едоковн Е.Т. // 1984")
        sampleLib.addBooks("Кому нужны художники без нот // Зачемнапис А.Н., Количест В.О., Необозначе Н.О. // 1999")
        assertEquals(thirdOne, sampleLib.shortestNameBook())

        sampleLib = Library()
        sampleLib.addBooks(fullList)
        assertEquals(firstOne, sampleLib.shortestNameBook())
    }
}