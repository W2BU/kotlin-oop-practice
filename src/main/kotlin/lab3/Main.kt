package lab3

fun main() {
    val notes: List<Note> = listOf(
        Note.TaskNote("ASAP", "Get the kotlin lab done by tomorrow", "2022-04-22T10:15:30"),
        Note.UrlNote("Habr", "Cool site", "habr.com"),
        Note.UrlNote("Leetcode goal", "Complete 10 more", "leetcode.com"),
        Note.TextNote("Just a thought", "What if not just we live in a matrix but a matrix lives within us"),
        Note.TaskNote("Granny coming", "", "2022-04-27T10:15:30"),
    )

    val myNotesService = NotesService(notes)
    println(myNotesService)

    myNotesService.add(Note.UrlNote("Check this out", "music", "spotify.com"))

    println("\nALL NOTES:\n" + myNotesService.getAllNotes())

    myNotesService.remove("Cool site")
    myNotesService.remove("Granny Coming")

    println("\nALL URLS:\n" + myNotesService.getAllUrls())
    println("\nALL TASKS:\n" + myNotesService.getAllTasks())
    println("\nALL TEXT NOTES:\n"+myNotesService.getAllTextNotes())

    println("\nFOUND BY TITLE \"Just a thought\":\n" + myNotesService.findByTitle("Just a thought"))
    println("\nFOUND BY TYPE \"UrlNote\":\n" + myNotesService.findByType(Note.UrlNote::class))
    println("\nSORTED BY TITLE:\n" + myNotesService.getSortedByTitle())
    println("\nSORTED BY DATE:\n" + myNotesService.getSortedByDate())

}