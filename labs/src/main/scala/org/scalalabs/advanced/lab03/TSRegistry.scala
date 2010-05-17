package org.scalalabs.advanced.lab03

import scala.reflect._

/**
 * User: arjan
 * Date: May 10, 2010
 *
 *
 */

object ManifestSample {
  /**
   * In Scala, just as in Java, generic types are erased at runtime. Scala does provide a way to obtain the type information however, via the
   * Manifest class. You can pass a Manifest as an implicit parameter to your method. The compiler than takes care of providing
   * the required information (i.e. you don't have to instantiate it or take care this in any way yourself).
   *
   * The class below provied an example of a Type Safe Registry class. The idea is to be able to do the following:
   *
   * val reg = new TSReg[String, Int]
   * reg.add("a", 1)
   * reg.add("b", 2)
   *
   * safeGet[Int]("a") -> returns Some(1)
   * safeGet[Int]("c") -> returns None
   * safeGet[String]("a") -> returns None
   *
   * so the given type passed to the safeGet method should match the return type of this registry.
   */
  class TSReg[A, B] {
    /**
     * TODO
     * Implement the map. Hint: Manifests can be passed to parameters, and also stored in values.
     * It is therefore possible to store the manifest that is implicitly passed on as parameter (in the implentation of the add method.)
     */
    private var map = Map.empty[A, (Manifest[_], B)]

    /**
     * TODO
     * Implement the add method. You should use the Manifest as an implicit parameter in order to know the
     * runtime type of value B, which we need to know to implement the safeGet method properly.
     */
    def add(k: A, v: B) = {/*TODO implement me */}

    /**
     * This is the method that should do the type safe get: i.e. it should return Some(value)
     * iff the given type parameter matches, or is a supertype of the value belonging to the key.
     */
    def safeGet[T](key: A): Option[T] = {
      val ov = map.get(key)
      //TODO implement using manifest
      None
    }
  }

}