package com.example.tictactoe.ui.theme

data class GameState(
    val playerCircleCount : Int = 0,
    val playerCrossCount : Int = 0,
    val drawCount : Int = 0,
    val hintText : String = "Player 'O' Turn",
    val currentTurn : BoardCellValue = BoardCellValue.CIRCLE,
    val hasWon : Boolean = false,
    val victoryType : VictoryType = VictoryType.NONE
)



enum class BoardCellValue{
    CIRCLE,
    CROSS,
    NONE
}

enum class VictoryType{
    NONE,
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAG0NAL1,
    DIAG0NAL2
}