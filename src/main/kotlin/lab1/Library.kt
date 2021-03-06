package lab1

 class Library(initial: List<Book> = emptyList()) {

    private val content: MutableList<Book> = mutableListOf()

    init {
        content.addAll(initial)
    }

    //  Assuming the request and data is correct
    fun addBooks(request: String) {
        val bookInfoDelimiter = "//"
        val authorsDelimiter = ','
        val sequenceNumberDelimiter = '.'

        if (request.isNotEmpty()) {
            val booksInfo: List<String> = request.lines()
            booksInfo.forEach {
                val data = it.splitToSequence(bookInfoDelimiter).toList()
                content.add(
                    Book(
                        data[0].substringAfter(sequenceNumberDelimiter).trim(), // name
                        data[1].split(authorsDelimiter).map { authorName -> authorName.trim() },// authors
                        data[2].trim().toInt()// yearOfPublication
                    )
                )
            }
        }
    }

    fun oldestBook(): Book? = content.minByOrNull { it.yearOfPublication }

    fun latestBook(): Book? = content.maxByOrNull { it.yearOfPublication }

    fun longestNameBook(): Book? = content.maxByOrNull { it.name.length }

    fun shortestNameBook(): Book? = content.minByOrNull { it.name.length }

    fun prettyPrint() = content.forEach { println("$it") }

    fun getSize(): Int = content.size

    fun getBooks(): List<Book> = content

    override fun toString(): String = "$content".replace("[", "").replace("]", "")
}

data class Book(val name: String, val authors: List<String>, val yearOfPublication: Int) {
    override fun toString() = "$name // $authors // $yearOfPublication".replace("[", "").replace("]", "")
}