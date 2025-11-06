package solutions.CHK

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckoutSolutionTest {

    private fun getInvalidInputs() = listOf("a", "A1", "z*", "G_", "*", "Aba", "ABCD1", "ABCa", "ABCDEFGHi")

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
        Arguments.of("F", 10),
        Arguments.of("G", 20),
        Arguments.of("H", 10),
        Arguments.of("I", 35),
        Arguments.of("J", 60),
        Arguments.of("K", 70),
        Arguments.of("L", 90),
        Arguments.of("M", 15),
        Arguments.of("N", 40),
        Arguments.of("O", 10),
        Arguments.of("P", 50),
        Arguments.of("Q", 30),
        Arguments.of("R", 50),
        Arguments.of("S", 20),
        Arguments.of("T", 20),
        Arguments.of("U", 40),
        Arguments.of("V", 50),
        Arguments.of("W", 20),
        Arguments.of("X", 17),
        Arguments.of("Y", 20),
        Arguments.of("Z", 21)


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
        Arguments.of("HHHHH", 45), // 5H for 45
        Arguments.of("HHHHHHHHHH", 80), //10H 80
        Arguments.of("KK", 120),
        Arguments.of("NNNM", 120),
        Arguments.of("PPPPP", 200), //5P FOR 200
        Arguments.of("QQQ", 80), //3Q FOR 80
        Arguments.of("RRRQ", 150), // 3R GET ONE Q FREE
        Arguments.of("UUUU", 120), // 4U GET ONE U FREE
        Arguments.of("VV", 90), // 2V FOR 90
        Arguments.of("VVV", 130), // 3V FOR 130
        Arguments.of("VVVV", 180), // 3v for 130 +1v for 50
        Arguments.of("VVVVV", 220), // 3v for 130 +2v for 90
        Arguments.of("VVVVVV", 260), // 3v for 130+ 3v for 130
        Arguments.of("STX", 45),
        Arguments.of("SXY", 45),
        Arguments.of("STXYZ", 45 + 37), // 3 FOR 45 + remaining x(17) Y(20)
        Arguments.of("SSS", 45),
        Arguments.of("SST", 45),
        Arguments.of("SSTX", 62),
        Arguments.of("XYZXYZ", 90), // 6 items = 2x(3 for 45)


    )


    @ParameterizedTest
    @MethodSource("getMultipleItemsInputs")
    fun `checkout should calculate totals with multiple items and apply offers`(input: String, expected: Int) {
        val checkout = CheckoutSolution()
        checkout.checkout(input) shouldBe expected
    }

}