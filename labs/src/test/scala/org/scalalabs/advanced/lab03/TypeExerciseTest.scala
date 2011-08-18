package org.scalalabs.advanced.lab03

import org.junit.{ Test }
import org.junit.Assert._

/**
 * See @TypeExercise
 */

class TypeExerciseTest {

  @Test
  def onlyMamalsWithSameDietCanShareAMeal {
    import FoodExercise._

    val Cow = new Mamal { val eats = Grass }
    val Horse = new Mamal { val eats = Grass }
    val Shark = new Mamal { val eats = Fish }
    val Jake = new Mamal { val eats = Pizza }
    val Peet = new Mamal { val eats = Pizza }

    Cow.joinDinnerWith(Horse)
    Jake.joinDinnerWith(Peet)
    // TODO: shouldn't compile
    // Cow.joinDinnerWith(Jake)
  }

  @Test
  def churchBooleanTypes = {
    // TODO implement the Church Boolean data types so that the following line compiles:
    // val ctrue: CTrue#cond[Int, String] = 10
    // TODO and the following line should not compile:
    // val ctrue2: CTrue#cond[Int, String] = "10"
    // // error: type mismatch;
    // // found   : java.lang.String("10")
    // // required: Int

    // TODO and the following line should again compile:
    // val cfalse: CFalse#cond[Int,String] = "10"
  }

  @Test
  def churchNaturalNumbers = {
    // TODO define the Church numerals using Scala traits/types/classes or whatever you can think of.
    // Then uncomment the lines below so that the following compiles:
    // import ChurchEncoding._
    // 
    // assertEquals(Equals[two, one plus one], Equals())
    // 
    // type _1 = zero#succ
    // type _2 = _1#succ
    // assertEquals(Equals[_1, one], Equals())
    // assertEquals(Equals[_2, two], Equals())
  }

  @Test
  def typeSafeRegistry = {
    import ManifestExercise._

    val tsReg = new TSReg[Int, String]

    tsReg.add(1, "Scala")
    tsReg.add(2, "Haskell")

    assertEquals(Some("Scala"), tsReg.safeGet[String](1))
    assertEquals(Some("Haskell"), tsReg.safeGet[String](2))
    assertEquals(None, tsReg.safeGet[String](3))

    // the following returns a None, since the get has been made typeSafe
    assertEquals(None, tsReg.safeGet[Int](1))
  }

}
