package solutions.CHK

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckoutSolutionTest {

    private fun getInvalidInputs() = listOf("a", "A1", "z", "G", "*", "Aba", "ABCD1", "ABCa")

    @ParameterizedTest
    @MethodSource("getInvalidInputs")
    fun `checkout should return -1 if any illegal input is provided`(input: String) {
        val checkout = CheckoutSolution()
        checkout.checkout(input) shouldBe -1
    }


    private fun getSingeItemInputs() = listOf(
        Arguments.of("A", 50),
        Arguments.of("B", 30),
        Arguments.of("C", 20),
        Arguments.of("D", 15),
        Arguments.of("E", 40),
        Arguments.of("F", 10)

    )

    @ParameterizedTest
    @MethodSource("getSingeItemInputs")
    fun `checkout should calculate totals for single items`(input: String, expected: Int) {
        val checkout = CheckoutSolution()
        checkout.checkout(input) shouldBe expected
    }

    private fun getMultipleItemsInputs() = listOf(
        Arguments.of("AB", 80),
        Arguments.of("ABC", 100),
        Arguments.of("ABCD", 115),
        Arguments.of("AA", 100),
        Arguments.of("BB", 45), // 2B OFFER
        Arguments.of("AAA", 130), //3A OFFER
        Arguments.of("AAAABBB", 255), // 3A OFFER +1A PLUS 2B OFFER PLUS 1
        Arguments.of("EEB", 80), // 2E get 1B free
        Arguments.of("EEBB", 110), //2E get 1B free, 1B paid since it's cheaper than 2B for 45
        Arguments.of("ABCDE", 155), //50+30+20+15+40=155,
        Arguments.of("FFFFFF", 40), // 2F plus 1 free and another 2Fs plus 1 free
        Arguments.of("EEBBFFFFFF", 150), //conbination of EEBB plus 6Fs


    )


    @ParameterizedTest
    @MethodSource("getMultipleItemsInputs")
    fun `checkout should calculate totals with multiple items and apply offers`(input: String, expected: Int) {
        val checkout = CheckoutSolution()
        checkout.checkout(input) shouldBe expected
    }

}

