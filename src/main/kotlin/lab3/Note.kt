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

        override fun equals(other: Any?): Boolean =
            other is TextNote &&
            title == other.title &&
            description == other.description &&
            date == other.date


        override fun hashCode(): Int {
            return title.hashCode() + 31 * description.hashCode()
        }

        override fun toString(): String =
            "\n$title\n$description\n$date\n"
    }

    class TaskNote(
        title: String,
        description: String,
        private val deadline: String = "",
    ) : Note(title, description) {

        override fun equals(other: Any?): Boolean =
            other is TaskNote &&
            title == other.title &&
            description == other.description &&
            deadline == other.deadline &&
            date == other.date

        override fun hashCode(): Int {
            return title.hashCode() + 31 * deadline.hashCode() + 31 * description.hashCode()
        }

        override fun toString(): String =
            "\n$title\n$description\n$deadline\n$date\n"
    }

    class UrlNote(
        title: String,
        description: String,
        private val url: String = "",
    ) : Note(title, url) {
        override fun toString(): String =
            "\n$title\n$description\n$date\n"

        override fun equals(other: Any?): Boolean =
            other is UrlNote &&
            title == other.title &&
            description == other.description &&
            url == other.url &&
            date == other.date

        override fun hashCode(): Int {
            return title.hashCode() + 31 * url.hashCode() + 31 * description.hashCode()
        }
    }
}
