package com.tafreshiali.numera.presentation.components

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.tafreshiali.numera.presentation.CalculatorMainScreen
import com.tafreshiali.numera.presentation.theme.design_sytem.NumeraAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorActionButtonComponentKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NumeraAppTheme {
                CalculatorMainScreen()
            }
        }
    }

    @Test
    fun checkIfTheCalculatorButtonsIsExists() {
        composeTestRule.onNodeWithContentDescription(label = "1")
            .assertExists(errorMessageOnFail = "There is no button with text 1 exists")

        composeTestRule.onNodeWithContentDescription(label = "2")
            .assertExists(errorMessageOnFail = "There is no button with text 2 exists")

        composeTestRule.onNodeWithContentDescription(label = "3")
            .assertExists(errorMessageOnFail = "There is no button with text 3 exists")

        composeTestRule.onNodeWithContentDescription(label = "4")
            .assertExists(errorMessageOnFail = "There is no button with text 4 exists")

        composeTestRule.onNodeWithContentDescription(label = "5")
            .assertExists(errorMessageOnFail = "There is no button with text 5 exists")

        composeTestRule.onNodeWithContentDescription(label = "6")
            .assertExists(errorMessageOnFail = "There is no button with text 6 exists")

        composeTestRule.onNodeWithContentDescription(label = "7")
            .assertExists(errorMessageOnFail = "There is no button with text 7 exists")

        composeTestRule.onNodeWithContentDescription(label = "8")
            .assertExists(errorMessageOnFail = "There is no button with text 8 exists")

        composeTestRule.onNodeWithContentDescription(label = "9")
            .assertExists(errorMessageOnFail = "There is no button with text 9 exists")

        composeTestRule.onNodeWithContentDescription(label = "0")
            .assertExists(errorMessageOnFail = "There is no button with text 0 exists")

        composeTestRule.onNodeWithContentDescription(label = ".")
            .assertExists(errorMessageOnFail = "There is no button with text . exists")

        composeTestRule.onNodeWithContentDescription(label = "+")
            .assertExists(errorMessageOnFail = "There is no button with text + exists")

        composeTestRule.onNodeWithContentDescription(label = "-")
            .assertExists(errorMessageOnFail = "There is no button with text - exists")

        composeTestRule.onNodeWithContentDescription(label = "x")
            .assertExists(errorMessageOnFail = "There is no button with text × exists")

        composeTestRule.onNodeWithContentDescription(label = "÷")
            .assertExists(errorMessageOnFail = "There is no button with text ÷ exists")

        composeTestRule.onNodeWithContentDescription(label = "()")
            .assertExists(errorMessageOnFail = "There is no button with text () exists")

        composeTestRule.onNodeWithContentDescription(label = "C")
            .assertExists(errorMessageOnFail = "There is no button with text C exists")

        composeTestRule.onNodeWithContentDescription(label = "=")
            .assertExists(errorMessageOnFail = "There is no button with text = exists")
    }

    @Test
    fun checkIfAllCalculatorButtonsAreClickable() {
        composeTestRule.onNodeWithContentDescription(label = "1")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "2")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "3")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "4")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "5")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "6")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "7")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "8")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "9")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "0")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = ".")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "+")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "-")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "x")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "÷")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "()")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "C")
            .assertHasClickAction()

        composeTestRule.onNodeWithContentDescription(label = "=")
            .assertHasClickAction()
    }

    @Test
    fun checkTheSumCalculationResultIsCorrect() {
        composeTestRule.onNodeWithContentDescription(label = "1").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("1")
        composeTestRule.onNodeWithContentDescription(label = "+").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("1+")
        composeTestRule.onNodeWithContentDescription(label = "2").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("1+2")
        composeTestRule.onNodeWithContentDescription(label = "tvCalculationResult")
            .assertTextEquals("3")
    }

    @Test
    fun checkTheParenthesesCalculationResultIsCorrect() {
        composeTestRule.onNodeWithContentDescription(label = "()").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("(")

        composeTestRule.onNodeWithContentDescription(label = "-").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("(-")

        composeTestRule.onNodeWithContentDescription(label = "5").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("(-5")

        composeTestRule.onNodeWithContentDescription(label = "()").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("(-5)")

        composeTestRule.onNodeWithContentDescription(label = "+").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("(-5)+")

        composeTestRule.onNodeWithContentDescription(label = "2").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("(-5)+2")

        composeTestRule.onNodeWithContentDescription(label = "tvCalculationResult")
            .assertTextEquals("-3")
    }

    @Test
    fun checkTheMultiplicationCalculationResultIsCorrect() {
        composeTestRule.onNodeWithContentDescription(label = "10").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("10")
        composeTestRule.onNodeWithContentDescription(label = "x").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("10x")

        composeTestRule.onNodeWithContentDescription(label = "2").performClick()
        composeTestRule.onNodeWithContentDescription(label = "tvCurrentExpression")
            .assertTextEquals("10x2")
        composeTestRule.onNodeWithContentDescription(label = "tvCalculationResult")
            .assertTextEquals("20")
    }
}
