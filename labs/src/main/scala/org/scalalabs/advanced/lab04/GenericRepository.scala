package org.scalalabs.advanced.lab04

import org.scala_libs.jpa.ScalaEntityManager

/**
 * Interface of a generic dao with basic persistency
 * methods
 */
trait GenericDao[T <: { var id:Long}] {
   def findAll() : List[T]
   def save(entity:T) :T
   def remove(entity:T)(implicit m: Manifest[T]):Unit
   def findById(id:Any)(implicit m: Manifest[T]) : T
}

/**
 * Implement an abstract, type independent Dao that
 * extends the GenericDao trait and implements the following methods:
 * - findById
 * - save
 * - remove
 * */
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
