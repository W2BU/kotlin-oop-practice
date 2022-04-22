package lab3

import org.apache.logging.log4j.LogManager

class NotesService(initList: List<Note> = emptyList()) : INotesService {

    private val logger = LogManager.getLogger(NotesService::class.java)
    private val notes: MutableList<Note> = mutableListOf()

    init {
        notes.addAll(initList)
    }

    override fun add(note: Note) {
        notes.add(note)
        logger.info("Added existing note")
    }

    // Removes all notes with the specified title
    override fun remove(title: String) {
        notes.removeIf { it.title == title }
        logger.info("Removed note with title $title")
    }

    override fun getAllNotes(): List<Note> {
        logger.info("getAllNotes call")
        return notes.toList()
    }

    override fun getAllTextNotes(): List<Note.TextNote> {
        logger.info("${this::getAllTextNotes.name} call")
        return notes.filterIsInstance<Note.TextNote>().toList()
    }

    override fun getAllTasks(): List<Note.TaskNote> {
        logger.info("${this::getAllTasks.name} call")
        return notes.filterIsInstance<Note.TaskNote>().toList()
    }

    override fun getAllUrls(): List<Note.UrlNote> {
        logger.info("${this::getAllUrls.name} call")
        return notes.filterIsInstance<Note.UrlNote>().toList()
    }

    override fun createTextNote(title: String, description: String): Note.TextNote {
        logger.info("TextNote created")
        return Note.TextNote(title, description)
    }

    override fun createTaskNote(title: String, description: String, deadline: String): Note.TaskNote {
        logger.info("TaskNote created")
        return Note.TaskNote(title, description, deadline)
    }

    override fun createUrlNote(title: String, url: String): Note.UrlNote {
        logger.info("UrlNote created")
        return Note.UrlNote(title, url)
    }

    override fun findByTitle(title: String): List<Note> {
        logger.info("${this::findByTitle.name} call")
        return notes.filter { it.title == title }
    }
    override fun findByType(typename: String): List<Note> {
        logger.info("${this::findByTitle.name} call")
        return notes.filter { it.javaClass.simpleName == typename }
    }

    override fun getSortedByTitle(): List<Note> {
        logger.info("${this::getSortedByTitle.name} call")
        return notes.sortedBy { it.title }.toList()
    }


    override fun getSortedByDate(): List<Note> {
        logger.info("${this::getSortedByDate.name} call")
        return notes.sortedBy { it.date }.toList()
    }


    override fun toString(): String {
        val sb = StringBuilder()
        for (note in notes) {
            sb.append(note.toString())
        }
        return "$sb"
    }
}