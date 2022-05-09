package lab4.controller

import lab4.model.Model
import lab4.model.State.*
import lab4.model.Move.*

class Controller(private val model: Model) {
    init {
        mainMenu()
    }

    private fun mainMenu() {
        println("1 - continue")
        println("2 - start new game")
        val input = readln()
        try {
            when(input.toInt()) {
                1 -> startGame()
                2 -> newGame()
            }
        } catch (e: Exception) {
            println("Unknown option")
        }
    }

    private fun newGame() {
        println("Enter name of the file with maze")
        val filename = readln()
        model.changeMaze("src/main/kotlin/lab4/$filename")
        startGame()
    }

    private fun startGame() {
        while (model.state == NOT_REACHED) {
            when(readln()) {
                "w" -> model.doMove(UP)
                "a" -> model.doMove(LEFT)
                "s" -> model.doMove(DOWN)
                "d" -> model.doMove(RIGHT)
                "v" -> model.save()
                else -> println("Unknown input")
            }
        }
    }
}