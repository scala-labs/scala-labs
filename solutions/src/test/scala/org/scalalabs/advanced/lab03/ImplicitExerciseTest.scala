package org.scalalabs.advanced.lab03

import org.specs2.mutable.Specification

/**
 * @see ImplicitExercise
 */
class ImplicitExerciseTest extends Specification {

  "implicit exercise" should {
    "should add ints and strings" in {

      import ImplicitExercise._

      10 ==== add(List(1, 2, 3, 4))
      "1234" ==== add(List("1", "2", "3", "4"))
    }

    "nicer add ints and strings" in {

      import ImplicitExercise._

      10 ==== List(1, 2, 3, 4).add
      "1234" ==== List("1", "2", "3", "4").add
    }

    "add using numerics" in {

      import ImplicitExercise._

      150 ==== add(10, 20, 30, 40, 50)
    }

    "should order using implicit ord" in {
      20 ==== Ord[Int].max(List(10, 20, 3, 4, 5))
      3 ==== Ord[Int].min(List(10, 20, 3, 4, 5))

      "brown" ==== Ord[String].min(
        List(
          "the",
          "quick",
          "brown",
          "fox",
          "jumped",
          "over",
          "the",
          "lazy",
          "dog"))
      "the" ==== Ord[String].max(
        List(
          "The",
          "quick",
          "brown",
          "fox",
          "jumped",
          "over",
          "the",
          "lazy",
          "dog"))

      "A" ==== Ord[Int].minFor[String](
        List("A", "sentence", "of", "various", "lengths"),
        (t => t.length))
      "sentence" ==== Ord[Int].maxFor[String](
        List("A", "sentence", "of", "various", "lengths"),
        (t => t.length))
    }

    "use even more awesome implicits and types for ordering lists" in {

      import ImplicitExercise._

      20 ==== List(10, 20, 3, 4, 5).mymax
      3 ==== List(10, 20, 3, 4, 5).mymin

      "jumped" ==== List(
        "the",
        "quick",
        "brown",
        "fox",
        "jumped",
        "over",
        "the",
        "lazy",
        "dog").mymax(Ord[Int].on[String](t => t.length))
      "the" ==== List(
        "the",
        "quick",
        "brown",
        "fox",
        "jumped",
        "over",
        "the",
        "lazy",
        "dog").mymin(Ord[Int].on[String](t => t.length))
    }

    "a simple monad illustration" in {

      import Monads._

      implicit def toMA[M[_], A](ma: M[A]) = new MA[M, A] {
        val value: M[A] = ma
      }

      noValue === just(3).bind(x => if (x % 2 == 0) just(x - 1) else noValue)
      just(3) === just(4).bind(x => if (x % 2 == 0) just(x - 1) else noValue)
      just(7) === just(4).bind(x => just(x + 1)).bind(x => just(x + 2))
      noValue === just(4)
        .bind(x => just(x + 1))
        .bind(x => just(x + 2))
        .bind(x => noValue)

      List(1) ==== inject[List, Int](1)
      Just("Scala is great") === inject[Maybe, String]("Scala").bind(x =>
        just(x + " is great"))

      println(List(1) bind (x => List(x + 2)))
      List('T', 'h', 'e', 'q', 'u', 'i', 'c', 'k', 'b', 'r', 'o', 'w', 'n', 'f',
        'o', 'x') === List("The", "quick", "brown", "fox").bind(x => x.toList)

    }
  }
}
