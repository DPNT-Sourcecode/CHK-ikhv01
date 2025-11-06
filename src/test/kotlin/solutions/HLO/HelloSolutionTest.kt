package solutions.HLO


import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelloSolutionTest {


    @ParameterizedTest
    fun `it should properly say hello to the provided name`() {
        val hello = HelloSolution()
        hello.hello("Dimi") shouldBe "Hello, World!"
    }
}
