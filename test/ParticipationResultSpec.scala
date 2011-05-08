import java.util.{EmptyStackException, Stack}
import models.{County, ParticipationResult}
import org.junit.Assert._
import org.scalatest.Spec
import org.scalatest.matchers.ShouldMatchers

class ParticipationResultSpec extends Spec with ShouldMatchers {

  describe("A Stack") {

    describe("(when empty)") {

      val stack = new Stack[Int]

      it("should be empty") {
        stack should be('empty)
      }

      it("should complain when popped") {
        evaluating {
          stack.pop()
        } should produce[EmptyStackException]
      }
    }
  }

  describe("ParticipationResult") {
    val county1 = new County(1, "Tartumaa", 50)

    it ("counts participation in percents") {
      var votesPerDate : Array[java.lang.Integer] = Array(10, 20, 30, 40, 50)
      var participation = new ParticipationResult(county1, votesPerDate)

      var expectedParticipationPerDate : Array[Float] = Array(20, 40, 60, 80, 100)
      assertArrayEquals(expectedParticipationPerDate, participation.getParticipationPerDay, 0.00001f)
    }
  }
}