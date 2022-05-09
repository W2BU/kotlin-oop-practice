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
    REACHED,
    NOT_REACHED,
}

enum class Move {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    fun deltaX(): Int {
        return when(this) {
            LEFT -> -1
            RIGHT -> 1
            else -> 0
        }
    }

    fun deltaY(): Int {
        return when(this) {
            DOWN -> 1
            UP -> -1
            else -> 0
        }
    }
}
private const val PATH_PREFIX: String = "src/main/kotlin/lab4/"
private const val DEFAULT_SAVE_FILE_NAME: String = PATH_PREFIX + "save.txt"

interface ModelListener {
    fun onModelChange()
}

class Model {
    private var board: MutableList<MutableList<Cell>> = loadFromFile(DEFAULT_SAVE_FILE_NAME)

    private val listeners: MutableSet<ModelListener> = mutableSetOf()

    var state: State = State.NOT_REACHED
        private set

    //  Player position
    private var playerX: Int = 0
    private var playerY: Int = 0

    //  Maze properties
    private var mazeHeight: Int = 0
    private var mazeWidth: Int = 0


    fun addModelListener(listener: ModelListener) = listeners.add(listener)
    fun removeModelListener(listener: ModelListener) = listeners.remove(listener)
    private fun notifyModelListeners() {
        for (listener in listeners) {
            listener.onModelChange()
        }
    }

    fun changeMaze(newMazeFilename: String) {
        board = loadFromFile(newMazeFilename)
    }

    private fun loadFromFile(filename: String): MutableList<MutableList<Cell>> {
        val textMaze: List<String> = try {
            FileReader(filename).buffered().readLines()
        } catch (e: IOException) {
            println("Can't find the $filename")
            listOf(Cell.WALL.toString()) // return
        }
        mazeHeight = textMaze.size
        mazeWidth = longestRow(textMaze)

        val newMaze = MutableList(mazeHeight) { MutableList(mazeWidth) { Cell.WALL } }
        for ((i, row) in textMaze.withIndex()) {
            for ((j, element) in row.withIndex()) {
                when(element.toString()) {
                    Cell.EXIT.toString() -> newMaze[i][j] = Cell.EXIT
                    Cell.PATH.toString() -> newMaze[i][j] = Cell.PATH
                    Cell.PLAYER.toString() -> {
                        newMaze[i][j] = Cell.PLAYER
                        playerX = j
                        playerY = i
                    }
                }
            }
        }
        return newMaze
    }

    private fun longestRow(list: List<String>): Int = list.maxOf { it.length }.toInt()

    fun doMove(currentMove: Move) {
        val nextX = playerX + currentMove.deltaX()
        val nextY = playerY + currentMove.deltaY()
        if (nextY in 0..mazeHeight &&
            nextX in 0..mazeWidth &&
            (board[nextY][nextX] != Cell.WALL)) {
            if (board[nextY][nextX] == Cell.EXIT) {
                state = State.REACHED
                println("You won")
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
            FileWriter(PATH_PREFIX + "save.txt").buffered().use { writer ->
                for (row in board) {
                    for (cell in row) {
                        writer.write(cell.toString())
                    }
                    writer.write("\n")
                }
            }
        } catch (e: IOException) {
            println("Whoops something went wrong")
        }
    }

    override fun toString(): String {
        return buildString {
            board.forEach {
                append(it.toString()).appendLine()
            }
        }
    }

}