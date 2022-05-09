package lab4

import lab4.view.ConsoleUi
import lab4.model.Model
import lab4.controller.Controller

fun main() {
    val model = Model()
    ConsoleUi(model)
    Controller(model)
}