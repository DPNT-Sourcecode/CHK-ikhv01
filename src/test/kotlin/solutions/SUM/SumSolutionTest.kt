package solutions.SUM

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SumSolutionTest {

    @Test
    fun sum() {
        Assertions.assertEquals(2, SumSolution().sum(1, 1))
    }

    @Test
    fun `it should sum 2 numbers`(){
        val sumSolution= SumSolution()
        sumSolution.sum(-1,1) shouldBe 0
    }
}