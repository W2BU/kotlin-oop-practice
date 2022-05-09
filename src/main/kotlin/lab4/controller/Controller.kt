package lab4.controller

import lab4.model.Model
import lab4.model.State.*
import lab4.model.Move.*
import lab4.model.PATH_PREFIX
import java.io.IOException

class Controller(private val model: Model) {
    init {
        startGame()
    }

    private fun startGame() {
        mainMenuInput()
        newGameMenuInput()
        gameInput()
    }

    private fun mainMenuInput() {
        while (model.state == MAIN_MENU) {
            when(readln()) {
                "1" -> model.continueGame()
                "2" -> model.newGame()
                else -> println("Unknown input")
            }
        }
    }

    private fun newGameMenuInput() {
        while (model.state == NEW_GAME_MENU) {
            model.changeMaze(
                PATH_PREFIX +
                    try {
                        val filename = readln()
                        filename
                    } catch(e: IOException) {
                        println(e.message)
                        "save.txt"
                    }
            )
        }
    }

    private fun gameInput() {
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