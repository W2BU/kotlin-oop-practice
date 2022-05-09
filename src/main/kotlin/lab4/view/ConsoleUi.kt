package lab4.view

import lab4.model.ModelListener
import lab4.model.Model

class ConsoleUi(private val model: Model) {
    init {
        val listener = object : ModelListener {
            override fun onModelChange() {
                drawScene()
            }
        }
        model.addModelListener(listener)
        drawScene()
    }
    private fun drawScene() {
        println(model)
    }
}