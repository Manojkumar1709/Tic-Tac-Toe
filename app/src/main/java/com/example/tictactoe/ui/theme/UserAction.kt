package com.example.tictactoe.ui.theme

sealed class UserAction{
    object PlayAgainButtonClicked : UserAction()

    data class BoardTapped(val cellNo : Int ) : UserAction()
}
