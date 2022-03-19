package lab1

data class Library(private val content: MutableList<Book> = mutableListOf()) {

    //  Assuming the request and data is correct
    fun addBooks(request: String) {
        val bookInfoDelimiter = "//"
        val authorsDelimiter = ','
        val sequenceNumberDelimiter = '.'
        val booksInfo: List<String> = request.lines()

        booksInfo.forEach {
            val data = it.splitToSequence(bookInfoDelimiter).toList()
            // Book(name, authors, yearOfPublication)
            content.add(Book(data[0].substringAfter(sequenceNumberDelimiter).trim(),
                             data[1].split(authorsDelimiter).map{ authorName -> authorName.trim() },
                             data[2].trim().toInt()))
        }
    }

    fun oldestBook(): Book? {
        return content.minByOrNull { it.yearOfPublication }
    }

    fun latestBook(): Book? {
        return content.maxByOrNull { it.yearOfPublication }
    }

    fun longestNameBook(): Book? {
        return content.maxByOrNull { it.name.length }
    }

    fun shortestNameBook(): Book? {
        return content.minByOrNull { it.name.length }
    }

    fun prettyPrint() = content.forEach {println("$it")}
}

data class Book(val name: String, val authors: List<String>, val yearOfPublication: Int) {
    override fun toString() = "$name // $authors // $yearOfPublication".replace("[", "").replace("]", "")
}

