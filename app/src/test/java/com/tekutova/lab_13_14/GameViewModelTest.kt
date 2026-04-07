package com.tekutova.lab_13_14


import com.tekutova.lab_13_14.data.MAX_NO_OF_WORDS
import com.tekutova.lab_13_14.data.SCORE_INCREASE
import com.tekutova.lab_13_14.ui_model.GameViewModel
import org.junit.Assert.*
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrabmledWord(currentGameUiState.currentScrambledWord)
        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrabmledWord(currentGameUiState.currentScrambledWord)
            assertEquals(expectedScore, currentGameUiState.score)
        }
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        assertTrue(currentGameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_Initialization_FirstWordLoaded(){
        val gameUiState = viewModel.uiState.value
        val unscrambledWord = getUnscrabmledWord(gameUiState.currentScrambledWord)
        assertNotEquals(unscrambledWord, gameUiState.currentScrambledWord)
        assertTrue(gameUiState.currentWordCount == 1)
        assertTrue(gameUiState.score == 0)
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectPlayerWord = "incorrect"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()
        val currentGameUiState = viewModel.uiState.value
        assertEquals(0,currentGameUiState.score)
        assertTrue(currentGameUiState.isGuessedWordWrong)
    }

    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset(){
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrabmledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value
        assertEquals(SCORE_INCREASE,currentGameUiState.score)
        assertFalse(currentGameUiState.isGuessedWordWrong)
    }

    private fun getUnscrabmledWord(scrambledWord: String): String{
        return com.tekutova.lab_13_14.data.allWords.firstOrNull{word -> scrambledWord.toSet() == word.toSet()} ?: ""

    }
}