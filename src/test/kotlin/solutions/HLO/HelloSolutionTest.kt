package solutions.HLO


import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelloSolutionTest {

    private fun getSomeStrings() = listOf("a", "s", "asdf", "aweqrt", "asdgf", "aasdfa", "a234", "", "blabls1234")

    @ParameterizedTest
    @MethodSource("getSomeStrings")
    fun `it should properly say hello to the provided name`(input: String) {
        val hello = HelloSolution()
        hello.hello(input) shouldBe "Hello, $input!"
    }
}
