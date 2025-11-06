package solutions.HLO

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HelloSolutionTest {

    @Test
    fun `it should properly say hello to the provided name`() {
        val hello = HelloSolution()
        hello.hello("Dimi") shouldBe "hello Dimi"
    }
}