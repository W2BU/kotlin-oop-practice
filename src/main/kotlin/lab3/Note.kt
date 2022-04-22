package lab3

import java.time.LocalDateTime

sealed class Note(
    val title: String = "",
    val description: String = "",
    val date: String = LocalDateTime.now().toString(),
) {

    class TextNote(
        title: String,
        description: String,
    ) : Note(title, description) {
        override fun toString(): String =
            "\n$title\n$description\n$date\n"
    }

    class TaskNote(
        title: String,
        description: String,
        val deadline: String = "",
    ) : Note(title, description) {
        override fun toString(): String =
            "\n$title\n$description\n$deadline\n$date\n"
    }

    class UrlNote(
        title: String,
        url: String = "",
    ) : Note(title, url) {
        override fun toString(): String =
            "\n$title\n$description\n$date\n"
    }
}
