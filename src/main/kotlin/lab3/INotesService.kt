package lab3

interface INotesService {
    fun add(note: Note)
    fun remove(title: String)

    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note.TextNote>
    fun getAllTasks(): List<Note.TaskNote>
    fun getAllUrls(): List<Note.UrlNote>

    fun createTextNote(title: String, description: String): Note.TextNote
    fun createTaskNote(title: String, description: String, deadline: String): Note.TaskNote
    fun createUrlNote(title: String, url: String): Note.UrlNote

    fun findByTitle(title: String): List<Note>
    fun findByType(typename: String): List<Note>

    fun getSortedByTitle(): List<Note>
    fun getSortedByDate(): List<Note>
}



