package com.example.view

import com.example.Styles
import com.example.tankVol
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val controller: MyController by inject()
    val inputH = SimpleIntegerProperty()
    val inputD = SimpleIntegerProperty()
    val inputVT = SimpleIntegerProperty()

    override val root = form {
        fieldset {
            field("H") {
                textfield(inputH)
            }
            field("D") {
                textfield(inputD)
            }
            field("VT") {
                textfield(inputVT)
            }

            button("Calculate") {
                action {
                    controller.calculateTankVol(inputH.value, inputD.value, inputVT.value)
                    inputH.value = 0
                }
            }
        }
    }
}

class MyController: Controller() {
    fun calculateTankVol(inputH: Int, inputD: Int, inputVT: Int): Int {
        val res = tankVol(inputH, inputD, inputVT)
        println("We get $res!")
        return res
    }
}