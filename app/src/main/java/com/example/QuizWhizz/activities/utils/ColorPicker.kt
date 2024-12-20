package com.example.QuizWhizz.activities.utils

object ColorPicker {
    val colors = arrayOf(
        "#26B0B6",
    "#DA8AF0",
    "#EAC3F4",

    "#F2E67C",
    "#F89A5E",
        "#E7DDFF",
        "#EE7575"

    )
    var currentColorIndex= 0
    fun getColor(): String{
        currentColorIndex = (currentColorIndex +1) % colors.size
        return colors[currentColorIndex]

    }
}