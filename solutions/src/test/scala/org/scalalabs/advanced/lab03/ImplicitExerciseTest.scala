package org.scalalabs.advanced.lab03

import org.junit.{Test, Before}
//import org.scalalabs.util.{LoggingTest, ScalaLabsConfig}
import org.scalatest.junit.JUnitSuite
import org.junit.Assert._

/**
 * Created by IntelliJ IDEA.
 * User: arjan
 * Date: Apr 9, 2010
 * Time: 2:43:06 PM
 * To change this template use File | Settings | File Templates.
 */

class ImplicitExerciseTest extends JUnitSuite {

//  @Before
//  def setup() {
//     initialize
//  }

  @Test
  def shouldAddIntsAndStrings = {
    import ImplicitExercise._

    assertEquals(10, add(List(1, 2, 3, 4)))
    assertEquals("1234", add(List("1", "2", "3", "4")))
  }


    @Test
    def nicerAddIntsAndStrings = {
      import ImplicitExercise._
      
      assertEquals(10, List(1, 2, 3, 4) add)
      assertEquals("1234", List("1", "2", "3", "4") add)
    }

  
}