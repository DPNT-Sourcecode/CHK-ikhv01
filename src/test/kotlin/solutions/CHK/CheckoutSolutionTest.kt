package solutions.CHK

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
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

}