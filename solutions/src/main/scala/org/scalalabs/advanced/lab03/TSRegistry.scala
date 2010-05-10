package org.scalalabs.advanced.lab03

import scala.reflect._

/**
 * User: arjan
 * Date: May 10, 2010
 * Time: 5:57:22 PM
 */

object ManifestSample {
  class TSReg[A, B] {
    private var map = Map.empty[A, (Manifest[_], B)]

    def add(k: A, v: B)(implicit m: Manifest[B]) {
      //    map = map(k) = (m, v)
      map = map updated (k, (m, v))
    }

    def safeGet[T](key: A)(implicit m: Manifest[T]): Option[T] = {
      val ov = map.get(key)
      ov match {
        case Some((ovm: Manifest[_], v: Any)) =>
          if (ovm <:< m) Some(v.asInstanceOf[T]) else None
        case _ => None
      }
    }
  }

}