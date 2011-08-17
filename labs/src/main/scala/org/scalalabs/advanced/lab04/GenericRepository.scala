package org.scalalabs.advanced.lab04

import org.scala_libs.jpa.ScalaEntityManager
import collection.mutable.Buffer
/**
 * Interface of a generic dao with basic persistency
 * methods
 */
trait GenericDao[T <: { var id: Long }] {
  def findAll(): Buffer[T]
  def save(entity: T): T
  def remove(entity: T)(implicit m: Manifest[T]): Unit
  def findById(id: Any)(implicit m: Manifest[T]): T
}

/**
 * Implement an abstract, type independent Dao that
 * extends the GenericDao trait and implements the following methods:
 * - findById
 * - save
 * - remove
 * In order to access the ScalaEntityManager make use of
 * the ScalaEntityManagerFactory trait
 */
abstract class GenericDaoImpl {
  //TODO impelement
}

/**
 * Implement a concrete Dao for the Director entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() method
 */
class DirectorDao {
  //TODO impelement
}

/**
 * Implement a concrete Dao for the Movie entity that
 * extends from the GenericDaoImpl. In addition, implement
 * the findAll() and findByTitle() method
 */
class MovieDao {
  //TODO impelement
}
