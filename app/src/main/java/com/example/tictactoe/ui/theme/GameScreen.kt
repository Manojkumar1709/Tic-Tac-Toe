package com.example.tictactoe.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen(
    viewModel: GameViewModel
){
    val state = viewModel.state

    Column(modifier = Modifier
        .fillMaxSize()
        .background(GrayBackground)
        .padding(horizontal = 30.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceEvenly) {

        Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {

            Text(text = "Player '0' : ${state.playerCircleCount}", fontSize = 16.sp)
            Text(text = "Draw : ${state.drawCount}", fontSize = 16.sp)
            Text(text = "Player 'X' : ${state.playerCrossCount}", fontSize = 16.sp)

        }

        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = BlueCustom
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(GrayBackground),
        contentAlignment = Alignment.Center
        ) {
                BoardBase()
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .aspectRatio(1f)){

                    viewModel.boardItems.forEach { (cellNo,boardCellValue) ->
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    ) {
                                        viewModel.onAction(
                                            UserAction.BoardTapped(cellNo)
                                        )
                                    },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {

                                AnimatedVisibility(
                                    visible = viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                                    enter = scaleIn(tween(1000))
                                ) {
                                    if (boardCellValue == BoardCellValue.CIRCLE){
                                        Circle()
                                    }
                                    else if(boardCellValue == BoardCellValue.CROSS) {
                                        Cross()
                                    }
                                }
                            }
                        }
                    }
                }

            Column(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))) {
                    DrawVictoryLine(state = state)
                }
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = state.hintText,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            )

            Button(onClick = {
                             viewModel.onAction(
                                 UserAction.PlayAgainButtonClicked
                             )
            },
            shape = RoundedCornerShape(5.dp),
            elevation = ButtonDefaults.elevation(5.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BlueCustom,
                contentColor = Color.White
            )
            ) {
                Text(
                    text = "Play Again",
                    fontSize = 16.sp)
            }
        }



    }

}

@Composable
fun DrawVictoryLine(
    state: GameState
){
    when(state.victoryType){
        VictoryType.NONE -> {}
        VictoryType.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryType.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryType.HORIZONTAL3 -> WinHorizontalLine3()
        VictoryType.VERTICAL1 -> WinVerticalLine1()
        VictoryType.VERTICAL2 -> WinVerticalLine2()
        VictoryType.VERTICAL3 -> WinVerticalLine3()
        VictoryType.DIAG0NAL1 -> WinDiagnolLine1()
        VictoryType.DIAG0NAL2 -> WinDiagnolLine2()
    }


}



@Preview(showBackground = true)
@Composable
fun GameScreenPreview(){
    GameScreen(viewModel = GameViewModel())
}
