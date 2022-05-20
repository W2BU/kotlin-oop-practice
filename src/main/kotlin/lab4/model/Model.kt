package lab4.model

import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

enum class Cell(private val textValue: String) {
    WALL("#"),
    PATH("-"),
    PLAYER("P"),
    EXIT("E");

    override fun toString(): String = textValue
}

enum class State {
    MAIN_MENU,
    NEW_GAME_MENU,
    CONTINUE_GAME_MENU,
    REACHED,
    NOT_REACHED,
}

enum class Move {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun deltaX(): Int {
        return when (this) {
            LEFT -> -1
            RIGHT -> 1
            else -> 0
        }
    }

    fun deltaY(): Int {
        return when (this) {
            DOWN -> 1
            UP -> -1
            else -> 0
        }
    }
}

const val PATH_PREFIX: String = "src/main/kotlin/lab4/"
private const val DEFAULT_SAVE_FILE_NAME: String = PATH_PREFIX + "save.txt"

fun loadFromFile(filename: String): List<List<Cell>> {
    val textMaze: List<String> = try {
        FileReader(filename).buffered().readLines()
    } catch (e: IOException) {
        println("Can't find the $filename")
        listOf(Cell.WALL.toString()) // return
    }

    val mazeHeight = textMaze.size
    val mazeWidth = textMaze.maxOf { it.length }.toInt() // longest row to equal other rows

    val newMaze = MutableList(mazeHeight) { MutableList(mazeWidth) { Cell.WALL } }
    for ((i, row) in textMaze.withIndex()) {
        for ((j, element) in row.withIndex()) {
            when (element.toString()) {
                Cell.EXIT.toString() -> newMaze[i][j] = Cell.EXIT
                Cell.PATH.toString() -> newMaze[i][j] = Cell.PATH
                Cell.PLAYER.toString() -> newMaze[i][j] = Cell.PLAYER
            }
        }
    }
    return newMaze
}


interface ModelListener {
    fun onModelChange()
}

class Model {
    private var board: MutableList<MutableList<Cell>> = mutableListOf()

    private val listeners: MutableSet<ModelListener> = mutableSetOf()

    var state: State = State.MAIN_MENU
        private set

    //  Player position
    private var playerX: Int = 0
    private var playerY: Int = 0

    //  Maze properties
    private var mazeHeight: Int = 0
    private var mazeWidth: Int = 0

    fun addModelListener(listener: ModelListener) = listeners.add(listener)
    private fun notifyModelListeners() {
        for (listener in listeners) {
            listener.onModelChange()
        }
    }

    fun doMove(currentMove: Move) {
        val nextX = playerX + currentMove.deltaX()
        val nextY = playerY + currentMove.deltaY()
        if (nextY in 0..mazeHeight &&
            nextX in 0..mazeWidth &&
            (board[nextY][nextX] != Cell.WALL)
        ) {
            if (board[nextY][nextX] == Cell.EXIT) {
                state = State.REACHED
            } else {
                board[playerY][playerX] = Cell.PATH
                board[nextY][nextX] = Cell.PLAYER
                playerX = nextX
                playerY = nextY
            }
        }
        notifyModelListeners()
    }

    fun save() {
        try {
            FileWriter(DEFAULT_SAVE_FILE_NAME).buffered().use { writer ->
                for (row in board) {
                    for (cell in row) {
                        writer.write(cell.toString())
                    }
                    writer.write("\n")
                }
            }
        } catch (e: IOException) {
            println("Whoops something wrong with save.txt")
        }
    }

    fun newGame() {
        state = State.NEW_GAME_MENU
        notifyModelListeners()
    }

    fun continueGame() {
        state = State.CONTINUE_GAME_MENU
        notifyModelListeners()
    }

    fun changeBoard(newBoard: List<List<Cell>>) {
        mazeHeight = newBoard.size
        mazeWidth = newBoard[0].size
        board = MutableList(mazeHeight) { MutableList(mazeWidth) { Cell.WALL } }
        for ((i, row) in newBoard.withIndex()) {
            for ((j, element) in row.withIndex()) {
                when (element) {
                    Cell.EXIT -> board[i][j] = Cell.EXIT
                    Cell.PATH -> board[i][j] = Cell.PATH
                    Cell.PLAYER -> {
                        board[i][j] = Cell.PLAYER
                        playerX = j
                        playerY = i
                    }
                    Cell.WALL -> board[i][j] = Cell.WALL
                }
            }
        }
        state = State.NOT_REACHED
        notifyModelListeners()
    }

    override fun toString(): String {
        return when (state) {
            State.MAIN_MENU -> "1 - continue\n2 - new game\n"
            State.NEW_GAME_MENU -> "Enter name of the file with maze"
            State.REACHED -> "You won"
            else -> {
                buildString {
                    board.forEach {
                        append(it.toString()).appendLine()
                    }
                }
            }
        }
    }
}
