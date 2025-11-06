package solutions.HLO

import io.kotest.matchers.shouldBe

class HelloSolutionTest {

    @ParameterizedTest
    fun `it should properly say hello to the provided name`() {
        val hello = HelloSolution()
        hello.hello("Dimi") shouldBe "Hello, World!"
    }
}