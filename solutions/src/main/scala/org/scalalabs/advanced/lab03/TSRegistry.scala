package org.scalalabs.advanced.lab03

import scala.reflect._
import scala.reflect.runtime.universe._

/**
 * In Scala, just as in Java, generic types are erased at runtime. Scala does provide a way to obtain the type information however, via the
 * Manifest class. You can pass a Manifest as an implicit parameter to your method. The compiler than takes care of providing
 * the required information (i.e. you don't have to instantiate it or take care this in any way yourself).
 */
class TSRegistry[A: TypeTag, B: TypeTag] {
  private var map = Map.empty[A, (Manifest[_], B)]

  def add(k: A, v: B)(implicit m: Manifest[B]): Unit = {
    map = map updated (k, (m, v))
  }

  def safeGet[T: TypeTag](key: A)(implicit m: Manifest[T]): Option[T] = {
    val ov = map.get(key)
    ov match {
      case Some((ovm: Manifest[_], v: B)) =>
        if (typeTag[B].tpe == typeTag[T].tpe)
          Some(v.asInstanceOf[T])
        else
          None
      case _ => None
    }
  }
}
