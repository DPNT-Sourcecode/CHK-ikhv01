package solutions.CHK

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckoutSolutionTest {

    private fun getInvalidInputs() = listOf("a", "A1", "z", "E", "*", "Aba")

    @ParameterizedTest
    @MethodSource("getInvalidInputs")
    fun `checkout should return -1 if any illegal input is provided`(input: String) {
        val checkout = CheckoutSolution()
        checkout.checkout(input) shouldBe -1
    }


    private fun getSingeItemInputs() = listOf(
        Arguments.of("A" to 50),
        Arguments.of("B" to 30),
        Arguments.of("C" to 20),
        Arguments.of("D" to 15),
        Arguments.of("" to 0)
    )

    @ParameterizedTest
    @MethodSource("getSingeItemInputs")
    fun `checkout should calculate totals for single items`(input: String, expected: Int) {
        val checkout = CheckoutSolution()
        checkout.checkout(input) shouldBe expected
    }

}